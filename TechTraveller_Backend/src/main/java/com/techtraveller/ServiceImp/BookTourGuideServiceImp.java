package com.techtraveller.ServiceImp;


import com.techtraveller.Dto.BookGuideDto;
import com.techtraveller.Entity.BookGuide;
import com.techtraveller.Repository.BookGuideRepository;
import com.techtraveller.Service.BookTourGuideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTourGuideServiceImp implements BookTourGuideService {

    @Autowired
    private BookGuideRepository bookGuideRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public BookGuideDto createBooking(BookGuideDto bookGuideDto) {
        BookGuide bookGuide = modelMapper.map(bookGuideDto, BookGuide.class);
        BookGuide savedBookGuide = bookGuideRepository.save(bookGuide);
        return modelMapper.map(savedBookGuide, BookGuideDto.class);
    }

    @Override
    public BookGuideDto updateBooking(String id, BookGuideDto bookGuideDto) {
        BookGuide existingBookGuide = bookGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        modelMapper.map(bookGuideDto, existingBookGuide);
        BookGuide updatedBookGuide = bookGuideRepository.save(existingBookGuide);
        return modelMapper.map(updatedBookGuide, BookGuideDto.class);
    }

    @Override
    public BookGuideDto getBookingById(String id) {
        BookGuide bookGuide = bookGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return modelMapper.map(bookGuide, BookGuideDto.class);
    }

    @Override
    public List<BookGuideDto> getAllBookings() {
        List<BookGuide> bookings = bookGuideRepository.findAll();
        return bookings.stream()
                .map(bookGuide -> modelMapper.map(bookGuide, BookGuideDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(String id) {
        BookGuide bookGuide = bookGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookGuideRepository.delete(bookGuide);
    }
}

