package com.techtraveller.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.techtraveller.Entity.BookGuide.BookingStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookGuideDto {

    private String id;
    private String touristId;
    private String tourGuideId;
    private Date startDate;
    private Date endDate;
    private double totalCost;
    private BookingStatus status;
    private String notes;
}

