package com.collage.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MaterialReference {

    @Id
    private String id;
    private String pdfName;
    private String description;

    @ManyToOne
	@JsonBackReference
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
}
