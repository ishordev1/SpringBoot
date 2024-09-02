package com.techtraveller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techtraveller.Dto.ApiResponse;
import com.techtraveller.Dto.TourGuideDto;
import com.techtraveller.Service.TourGuideService;

import java.util.List;

@RestController
@RequestMapping("/api/tour-guides")
public class TourGuideController {

    @Autowired
    private TourGuideService tourGuideService;

    @PostMapping
    public ResponseEntity<ApiResponse> createTourGuide(@RequestBody TourGuideDto tourGuideDto) {
    	
    	System.out.println(tourGuideDto);
        TourGuideDto createdTourGuide = tourGuideService.createTourGuide(tourGuideDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ApiResponse.<TourGuideDto>builder()
                                               .success(true)
                                               .message("Tour Guide created successfully")
                                               .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTourGuideById(@PathVariable String id) {
        TourGuideDto tourGuideDto = tourGuideService.getTourGuideById(id);
        return ResponseEntity.ok(ApiResponse.<TourGuideDto>builder()
                                           .success(true)
                                           .message("Tour Guide retrieved successfully")
                                           .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTourGuides() {
        List<TourGuideDto> tourGuideDtos = tourGuideService.getAllTourGuides();
        return ResponseEntity.ok(ApiResponse.<List<TourGuideDto>>builder()
                                           .success(true)
                                           .message("Tour Guides retrieved successfully")
                                           .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTourGuide(@PathVariable String id, @RequestBody TourGuideDto tourGuideDto) {
        TourGuideDto updatedTourGuide = tourGuideService.updateTourGuide(id, tourGuideDto);
        return ResponseEntity.ok(ApiResponse.<TourGuideDto>builder()
                                           .success(true)
                                           .message("Tour Guide updated successfully")
                                           .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTourGuide(@PathVariable String id) {
        tourGuideService.deleteTourGuide(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(ApiResponse.<Void>builder()
                                               .success(true)
                                               .message("Tour Guide deleted successfully")
                                               .build());
    }
}
