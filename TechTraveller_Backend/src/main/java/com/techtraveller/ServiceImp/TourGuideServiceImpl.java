package com.techtraveller.ServiceImp;


import com.techtraveller.Dto.TourGuideDto;
import com.techtraveller.Entity.TourGuide;
import com.techtraveller.Repository.TourGuideRepository;
import com.techtraveller.Service.TourGuideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TourGuideDto createTourGuide(TourGuideDto tourGuideDto) {
        TourGuide tourGuide = modelMapper.map(tourGuideDto, TourGuide.class);
        tourGuide.setCreatedDate(new Date());
        TourGuide savedTourGuide = tourGuideRepository.save(tourGuide);
        return modelMapper.map(savedTourGuide, TourGuideDto.class);
    }

    @Override
    public TourGuideDto updateTourGuide(String id, TourGuideDto tourGuideDto) {
        TourGuide existingTourGuide = tourGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Guide not found"));

        modelMapper.map(tourGuideDto, existingTourGuide);
        TourGuide updatedTourGuide = tourGuideRepository.save(existingTourGuide);
        return modelMapper.map(updatedTourGuide, TourGuideDto.class);
    }
    
    @Override
    public TourGuideDto getTourGuideById(String id) {
        TourGuide tourGuide = tourGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Guide not found"));
        return modelMapper.map(tourGuide, TourGuideDto.class);
    }

    @Override
    public List<TourGuideDto> getAllTourGuides() {
        List<TourGuide> tourGuides = tourGuideRepository.findAll();
        return tourGuides.stream()
                .map(tourGuide -> modelMapper.map(tourGuide, TourGuideDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTourGuide(String id) {
        TourGuide tourGuide = tourGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Guide not found"));
        tourGuideRepository.delete(tourGuide);
    }
}


