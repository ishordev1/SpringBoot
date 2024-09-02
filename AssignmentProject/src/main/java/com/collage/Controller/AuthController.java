package com.collage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collage.Dto.UserDto;

import com.collage.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/verify-email")
	ResponseEntity<UserDto> verify_Email(@RequestParam("token") String token){
		UserDto userDto = this.userService.getUserByEmailToken(token);
		if(userDto.getEmailVerified()) {
			return new ResponseEntity<>(userDto,HttpStatus.OK);
		}
		userDto.setEmailVerified(true);
		userDto.setIsActive(true);
		UserDto updateUser = this.userService.updateUser(userDto.getUserId(), userDto);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}

}
