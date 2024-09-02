package com.collage.Dto;

import java.util.Date;
import java.util.List;

import com.collage.Entity.Assignment;
import com.collage.Entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ClientDto {
	private String id;
//	private User user;
	private List<Assignment> assignmentsPost;
}
