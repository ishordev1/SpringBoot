package com.techtraveller.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.techtraveller.Entity.TourPackage.AvailabilityStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourPackageDto {

    private String id;
    private String tourGuideId;
    private String packageName;
    private String destination;
    private int durationDays;
    private double pricePerPerson;
    private Date startDate;
    private Date endDate;
    private String description;
    private String pickupLocation;
    private int numberOfSeats;
    private AvailabilityStatus availabilityStatus;
    private List<String> languageIds;
}
