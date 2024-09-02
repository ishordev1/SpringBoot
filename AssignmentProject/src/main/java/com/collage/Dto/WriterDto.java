package com.collage.Dto;

import java.util.Date;
import java.util.List;

import com.collage.Entity.Assignment;
import com.collage.Entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Data
public class WriterDto {

private String id;
private int rating;
//	User user;
    private List<Assignment> assignmentsWritten;
}
