package com.techtraveller.ServiceImp;


import com.techtraveller.Dto.TourPackageDto;
import com.techtraveller.Entity.TourPackage;
import com.techtraveller.Repository.TourPackageRepository;
import com.techtraveller.Service.TourPackageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourPackageServiceImp implements TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TourPackageDto createTourPackage(TourPackageDto tourPackageDto) {
        TourPackage tourPackage = modelMapper.map(tourPackageDto, TourPackage.class);
        TourPackage savedTourPackage = tourPackageRepository.save(tourPackage);
        return modelMapper.map(savedTourPackage, TourPackageDto.class);
    }

    @Override
    public TourPackageDto updateTourPackage(String id, TourPackageDto tourPackageDto) {
        TourPackage existingTourPackage = tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Package not found"));

        modelMapper.map(tourPackageDto, existingTourPackage);
        TourPackage updatedTourPackage = tourPackageRepository.save(existingTourPackage);
        return modelMapper.map(updatedTourPackage, TourPackageDto.class);
    }

    @Override
    public TourPackageDto getTourPackageById(String id) {
        TourPackage tourPackage = tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Package not found"));
        return modelMapper.map(tourPackage, TourPackageDto.class);
    }

    @Override
    public List<TourPackageDto> getAllTourPackages() {
        List<TourPackage> tourPackages = tourPackageRepository.findAll();
        return tourPackages.stream()
                .map(tourPackage -> modelMapper.map(tourPackage, TourPackageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTourPackage(String id) {
        TourPackage tourPackage = tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Package not found"));
        tourPackageRepository.delete(tourPackage);
    }
}

