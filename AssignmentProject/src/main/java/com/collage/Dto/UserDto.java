package com.collage.Dto;

import lombok.Data;

import java.util.Date;

import com.collage.Entity.Client;
import com.collage.Entity.Role;
import com.collage.Entity.Writer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class UserDto {

	private String userId;
	@Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid user Email")
	@NotBlank(message = "Email is must required")
	private String email;
	@NotBlank(message = "Password is must required")
	@Size(min = 4, max = 20)
	private String password;
	@NotBlank(message = "Name is must required.")
	private String firstName;
	private String lastName;
	private String profilePicture;
	private String role;
	@NotBlank(message = "Phone Number is must Required.")
	private String phoneNumber;
	private String gender;
	private Boolean isActive;
	private String college;
	private Date createdDate;
	private String emailToken;
	private Boolean emailVerified;
	

	private Client client;

	private Writer writer;
}
