package com.collage.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data


public class Assignment {

    @Id
    private String assignmentId;
    
    private String AssignmentQuestionFile;
    private String subject;
    private String description;
    private Date deadline;
    private Date createdAt;
    private String status;
    private String deliveryAddress;
    private String deliveryStatus;
    private Integer totalQuestions;
    private Double noteCopyPrice;
    private String paymentMode;
    private Double price;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonBackReference("assignment-client")
    private Client client;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "writer_id")
    @JsonBackReference("assignment-writer") 
    private Writer writer;

    
    
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MaterialReference> materialReferences;
}
