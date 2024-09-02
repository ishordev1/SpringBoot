package com.collage.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String role;
    private String phoneNumber;
    private String gender;
    private Boolean isActive;
    private String college;
    private boolean emailVerified;
    private String emailToken;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
//    @JsonManagedReference("client-user")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "writer_id")
//    @JsonManagedReference("writer-user")
    private Writer writer;
}
