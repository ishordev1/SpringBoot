package com.collage.Controller;

import com.collage.Dto.ApiResponse;
import com.collage.Dto.AssignmentDto;
import com.collage.Dto.ClientDto;
import com.collage.Dto.ImageResponse;
import com.collage.Dto.UserDto;
import com.collage.Entity.Assignment;
import com.collage.Exception.ResourceNotFoundException;
import com.collage.Repository.ClientRepository;
import com.collage.Repository.WriterRepository;
import com.collage.Service.AssignmentService;
import com.collage.Service.ClientService;
import com.collage.Service.FileService;
import com.collage.Service.WriterService;

import jakarta.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private FileService fileService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WriterService writerService;

	@Value("${assignment.file.path}")
	private String filePath;

	// Create a new assignment
	@PostMapping("/client/{clientId}")
	public ResponseEntity<AssignmentDto> createAssignment(@Validated @RequestBody AssignmentDto assignmentDto,
			@PathVariable String clientId) {
		System.out.println(clientId);
		AssignmentDto createdAssignment = assignmentService.createAssignment(assignmentDto, clientId);
		return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
	}

	// Update an existing assignment
	@PutMapping("/client/{clientId}/{assignmentId}")
	public ResponseEntity<AssignmentDto> updateAssignment(@Validated @RequestBody AssignmentDto assignmentDto,
			@PathVariable String clientId, @PathVariable String assignmentId) {
		this.clientService.getClientById(clientId);
		AssignmentDto updatedAssignment = assignmentService.updateAssignment(assignmentDto, assignmentId);
		return new ResponseEntity<>(updatedAssignment, HttpStatus.OK);
	}

	@DeleteMapping("/client/{clientId}/{assignmentId}")
	public ResponseEntity<ApiResponse> deleteAssignmentById(@PathVariable String clientId,
			@PathVariable String assignmentId) throws FileNotFoundException {
		this.clientService.getClientById(clientId);
		AssignmentDto assignmentDto = this.assignmentService.getAssignment(assignmentId);
		boolean check = this.assignmentService.deleteAssignmentById(assignmentId);
		if (check) {
			if (assignmentDto.getAssignmentQuestionFile() != null) {
				this.fileService.deleteFile(filePath, assignmentDto.getAssignmentQuestionFile());
			}
			return new ResponseEntity<>(new ApiResponse("delete successfully", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponse("somethings went wrong!..", false), HttpStatus.OK);
		}
	}

	// Get assignment by ID
	@GetMapping("/client/{clientId}/{assignmentId}")
	public ResponseEntity<AssignmentDto> getAssignment(@PathVariable String clientId,
			@PathVariable String assignmentId) {
		this.clientService.getClientById(clientId);
		AssignmentDto assignmentDto = assignmentService.getAssignment(assignmentId);
		return new ResponseEntity<>(assignmentDto, HttpStatus.OK);
	}

	// Get all assignments
	@GetMapping
	public ResponseEntity<List<AssignmentDto>> getAllAssignments() {
		List<AssignmentDto> assignments = assignmentService.allAssignment();
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}

	// Writer accepts an assignment
	@PutMapping("/{assignmentId}/accept/{writerId}")
	public ResponseEntity<AssignmentDto> writerAcceptAssignment(@PathVariable String assignmentId,
			@PathVariable String writerId) {
		this.writerService.getWriterById(writerId);
		AssignmentDto acceptedAssignment = assignmentService.writerAcceptAssignment(assignmentId, writerId);
		return new ResponseEntity<>(acceptedAssignment, HttpStatus.OK);
	}

	// Get all assignments by clientId
	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<AssignmentDto>> getAssignmentsByClientId(@PathVariable String clientId) {
		List<AssignmentDto> assignments = assignmentService.getAssignmentByClientId(clientId);
		return ResponseEntity.ok(assignments);
	}

	// Get all assignments by writerId
	@GetMapping("/writer/{writerId}")
	public ResponseEntity<List<AssignmentDto>> getAssignmentsByWriterId(@PathVariable String writerId) {
		List<AssignmentDto> assignments = assignmentService.getAssignmentAcceptedByWriter(writerId);
		return ResponseEntity.ok(assignments);
	}

	// Question File UPload
	@PostMapping("/client/{clientId}/pdf/{assignmentId}")
	public ResponseEntity<ImageResponse> uploadProfile(@RequestParam("assignmentFile") MultipartFile pdf,
			@PathVariable String assignmentId, @PathVariable String clientId) throws IOException {

		AssignmentDto assignmentDto = this.assignmentService.getAssignment(assignmentId);
		ClientDto clientDto = this.clientService.getClientById(clientId);

		String assignmentFileName = this.fileService.uploadImage(pdf, filePath, "assignment");
		assignmentDto.setAssignmentQuestionFile(assignmentFileName);

		this.assignmentService.updateAssignment(assignmentDto, assignmentId);

		ImageResponse imageResponse = ImageResponse.builder().imageName(assignmentFileName).success(true)
				.message("Image Uploaded Successfully").Status(HttpStatus.CREATED).build();

		return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
	}

	// Servie Question File
	@GetMapping("/file/{assignmentId}")
	public void serveAssignmentPDf(@PathVariable String assignmentId, HttpServletResponse response) throws IOException {
		AssignmentDto assignmentDto = this.assignmentService.getAssignment(assignmentId);
		if (assignmentDto.getAssignmentQuestionFile() != null) {

			InputStream resource = this.fileService.getResource(this.filePath,
					assignmentDto.getAssignmentQuestionFile());
			;
			response.setContentType(MediaType.ALL_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
		}

	}

	@DeleteMapping("/file/{assignmentId}")
	public ResponseEntity<ImageResponse> deleteImage(@PathVariable String assignmentId) throws FileNotFoundException {
		AssignmentDto assignmentDto = this.assignmentService.getAssignment(assignmentId);
		ImageResponse imgResponse = null;
		if (assignmentDto.getAssignmentQuestionFile() != null) {
			Boolean check = this.fileService.deleteFile(filePath, assignmentDto.getAssignmentQuestionFile());

			assignmentDto.setAssignmentQuestionFile(null);
			this.assignmentService.updateAssignment(assignmentDto, assignmentId);
			imgResponse = imgResponse.builder().imageName(assignmentDto.getAssignmentQuestionFile())
					.message("Image Delete Successfully").success(true).Status(HttpStatus.OK).build();
		} else {
			imgResponse = imgResponse.builder().message("Assignment File not found on the folder").success(false)
					.Status(HttpStatus.OK).build();
		}
		return new ResponseEntity<>(imgResponse, HttpStatus.OK);

	}

	@PutMapping("/client/{clientId}/file/{assignmentId}")
	public ResponseEntity<ApiResponse> updateImage(@RequestParam("assignmentFile") MultipartFile file,
			@PathVariable String clientId,
			@PathVariable String assignmentId) throws FileNotFoundException {
		ClientDto clientDto = this.clientService.getClientById(clientId);
		boolean updateFileCheck = this.fileService.updateFile(file, filePath, assignmentId, "assignment");
		ApiResponse apiResponse = new ApiResponse();
		if (updateFileCheck) {
			apiResponse.setMessage("successfully updated File.");
			apiResponse.setSuccess(true);
		} else {
			apiResponse.setMessage("not updated File.....");
			apiResponse.setSuccess(false);
		}

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
