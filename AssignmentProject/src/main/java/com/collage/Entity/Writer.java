package com.collage.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Writer {

	@Id
	private String id;
	private int rating;
//    @OneToOne(mappedBy = "writer")
//    @JsonBackReference("writer-user")
//    private User user;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("assignment-writer")
	private List<Assignment> assignmentsWritten;

}
