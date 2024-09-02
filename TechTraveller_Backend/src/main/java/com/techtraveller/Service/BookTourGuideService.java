package com.techtraveller.Service;


import com.techtraveller.Dto.BookGuideDto;
import java.util.List;

public interface BookTourGuideService {

    BookGuideDto createBooking(BookGuideDto bookGuideDto);

    BookGuideDto updateBooking(String id, BookGuideDto bookGuideDto);

    BookGuideDto getBookingById(String id);

    List<BookGuideDto> getAllBookings();

    void deleteBooking(String id);
}
