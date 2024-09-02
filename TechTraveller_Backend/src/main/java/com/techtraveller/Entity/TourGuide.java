package com.techtraveller.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Table(name = "tour_guides")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    private String gender;
    private String address;
    private boolean isActive = true;
    

    @Enumerated(EnumType.STRING)
    private Role role;
    private Date createdDate;
    private String experience;

    private String description;

    private double pricePerDay;

    private double rating = 0.0;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;
    @OneToMany(mappedBy = "tourGuide")
    private List<BookGuide> bookings = new ArrayList<>();
    @ManyToMany
    @JoinTable(
        name = "tour_guide_languages",
        joinColumns = @JoinColumn(name = "tour_guide_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages = new ArrayList<>();

    @OneToMany(mappedBy = "tourGuide")
    private List<TourPackage> tourPackages = new ArrayList<>();
    
    
    public enum AvailabilityStatus {
        AVAILABLE,
        ON_HOLIDAY,
        CREATED_PACKAGE,
        BOOKED
    }


}

