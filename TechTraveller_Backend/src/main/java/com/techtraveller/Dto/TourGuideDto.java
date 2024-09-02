package com.techtraveller.Dto;


import com.techtraveller.Entity.TourGuide.AvailabilityStatus;
import com.techtraveller.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourGuideDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String address;
    private Boolean isActive;
    private Role role;
    private Date createdDate;
    private String experience;
    private String description;
    private double pricePerDay;
    private double rating;   private String isApproval;
    private AvailabilityStatus availabilityStatus;
    private List<String> languages;
}
