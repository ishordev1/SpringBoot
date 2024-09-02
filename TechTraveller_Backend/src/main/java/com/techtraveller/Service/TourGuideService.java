package com.techtraveller.Service;


import com.techtraveller.Dto.TourGuideDto;
import java.util.List;

public interface TourGuideService {

    TourGuideDto createTourGuide(TourGuideDto tourGuideDto);

    TourGuideDto updateTourGuide(String id, TourGuideDto tourGuideDto);

    TourGuideDto getTourGuideById(String id);

    List<TourGuideDto> getAllTourGuides();

    void deleteTourGuide(String id);
}
