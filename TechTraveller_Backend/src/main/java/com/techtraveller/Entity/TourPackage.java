package com.techtraveller.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

import com.techtraveller.Entity.BookGuide.BookingStatus;

@Entity
@Table(name = "tour_packages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "tour_guide_id", nullable = false)
    private TourGuide tourGuide;

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private double pricePerPerson;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private int numberOfSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    @ManyToMany
    @JoinTable(
        name = "tour_package_languages",
        joinColumns = @JoinColumn(name = "tour_package_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages = new ArrayList<>();

    public enum AvailabilityStatus {
        AVAILABLE,
        FULLY_BOOKED,
        CANCELLED
    }

    // Getters and setters
}
