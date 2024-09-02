package com.collage.Controller;

import com.collage.Dto.ApiResponse;
import com.collage.Dto.ImageResponse;
import com.collage.Dto.PageableResponse;
import com.collage.Dto.UserDto;
import com.collage.Service.FileService;
import com.collage.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	@Value("${user.profile.image.path}")
	String imageUploadPath;

	// Create a new user
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto) {
		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// Update an existing user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Validated @PathVariable String userId, @RequestBody UserDto userDto) {
		UserDto updatedUser = this.userService.updateUser(userId, userDto);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// Get user by ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
		UserDto userDto = this.userService.getUserById(userId);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	// Get all users
	@GetMapping
	public ResponseEntity<PageableResponse<UserDto>> getAllUser(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
	}

	// Delete user by ID
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		UserDto userDto = this.userService.getUserById(userId);
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}

	@PostMapping("/image/{userId}")
	public ResponseEntity<ImageResponse> uploadProfile(@RequestParam("userImage") MultipartFile image,
			@PathVariable String userId) throws IOException {
		UserDto userDto = this.userService.getUserById(userId);

		String imageName = this.fileService.uploadImage(image, imageUploadPath, "user");
		userDto.setProfilePicture(imageName);
		this.userService.updateUser(userId, userDto);

		ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true)
				.message("Image Uploaded Successfully").Status(HttpStatus.CREATED).build();

		return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
	}

	@GetMapping("/image/{userId}")
	public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
		UserDto userDto = this.userService.getUserById(userId);
		InputStream resource = this.fileService.getResource(imageUploadPath, userDto.getProfilePicture());

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	@DeleteMapping("/image/{userId}")
	public ResponseEntity<ImageResponse> deleteImage(@PathVariable String userId) throws FileNotFoundException {
		UserDto userDto = this.userService.getUserById(userId);
		String oldPicName = userDto.getProfilePicture();
		Boolean check = this.fileService.deleteFile(imageUploadPath, userDto.getProfilePicture());
		ImageResponse imgResponse = null;

		if (check) {
			userDto.setProfilePicture("defaultProfile.jpg");
			this.userService.updateUser(userId, userDto);
			imgResponse = imgResponse.builder().imageName(oldPicName).message("Image Delete Successfully").success(true)
					.Status(HttpStatus.OK).build();
		} else {
			imgResponse = imgResponse.builder().message("Image not delete").success(false).Status(HttpStatus.OK)
					.build();
		}
		return new ResponseEntity<>(imgResponse, HttpStatus.OK);

	}

	@PutMapping("/image/{userId}")
	public ResponseEntity<ApiResponse> updateImage(@RequestParam("userImage") MultipartFile image,
			@PathVariable String userId) throws FileNotFoundException {

		boolean result = this.fileService.updateFile(image, imageUploadPath, userId, "profile");
		ApiResponse apiResponse = new ApiResponse();
		if (result) {
			apiResponse.setMessage("successfully updated image.");
			apiResponse.setSuccess(true);
		} else {
			apiResponse.setMessage("not updated image.");
			apiResponse.setSuccess(false);
		}

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
