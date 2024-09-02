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
public class Client {

	@Id
	private String id;
	
//	@OneToOne(mappedBy = "client")
//	@JsonBackReference("client-user")
//	private User user;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	  @JsonManagedReference("assignment-client")
	private List<Assignment> assignmentsPost;
	
}
