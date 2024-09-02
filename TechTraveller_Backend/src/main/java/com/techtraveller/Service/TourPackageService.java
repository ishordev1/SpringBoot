package com.techtraveller.Service;

import com.techtraveller.Dto.TourPackageDto;
import java.util.List;

public interface TourPackageService {

    TourPackageDto createTourPackage(TourPackageDto tourPackageDto);

    TourPackageDto updateTourPackage(String id, TourPackageDto tourPackageDto);

    TourPackageDto getTourPackageById(String id);

    List<TourPackageDto> getAllTourPackages();

    void deleteTourPackage(String id);
}
