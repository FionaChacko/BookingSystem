package com.trust.Booking.service;

import com.trust.Booking.request.UserRequest;
import com.trust.Booking.response.UserResponse;

import java.util.List;

public interface BookingService {
    UserResponse saveBook(UserRequest request);

    UserResponse updateBook(UserRequest request);

    String deleteBook(int id);

    UserResponse getBooking(int id);

    List<UserResponse> getAllBooking();
}
