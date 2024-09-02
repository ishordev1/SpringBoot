package com.collage.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Data
public class AssignmentDto {

    private String assignmentId;
//    @NotBlank(message="Question is must required")
    private String AssignmentQuestionFile;
    @NotBlank(message="Subject is must required")
    private String subject;
    private String description;
//    @NotBlank(message="Deadline Date is must required")
    private Date deadline;
    private Date createdAt;
    private String status;
    @NotBlank(message="Delevery address is must required.")
    private String deliveryAddress;
    private String deliveryStatus;

    private Integer totalQuestions;
    private Double noteCopyPrice;
    private String paymentMode;
    private Double price;
    private List<MaterialReferenceDto> materialReferences;
    
    
    private ClientDto client; 
    private WriterDto writer; 

}
