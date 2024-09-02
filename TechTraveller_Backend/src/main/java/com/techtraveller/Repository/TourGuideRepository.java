package com.techtraveller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtraveller.Entity.TourGuide;

public interface TourGuideRepository extends JpaRepository<TourGuide, String>{

}
