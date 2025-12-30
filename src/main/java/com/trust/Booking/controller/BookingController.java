package com.trust.Booking.controller;

import com.trust.Booking.model.Register;
import com.trust.Booking.request.UserRequest;
import com.trust.Booking.response.UserResponse;
import com.trust.Booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/register")
@Slf4j
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<UserResponse> createBook(@RequestBody UserRequest request){
        log.info("Booking controller: createBooking starts with request {}",request);
        UserResponse response = bookingService.saveBook(request);
        log.info("BookingController: createBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<UserResponse> updateBook(@RequestBody UserRequest request){
        log.info("Booking controller: updateBooking starts with request {}",request);
        UserResponse response = bookingService.updateBook(request);
        log.info("BookingController: updateBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        log.info("Booking controller: deleteBooking starts with id {}",id);
        String response = bookingService.deleteBook(id);
        log.info("BookingController: deleteBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getBook(@PathVariable int id){
        log.info("Booking controller: getBooking starts with id {}",id);
        UserResponse response = bookingService.getBooking(id);
        log.info("BookingController: getBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllBook(@RequestHeader(value = "X-Page-Number", defaultValue = "0") int page,
                                                         @RequestHeader(value = "X-Page-Size", defaultValue = "10") int size){
        log.info("Booking controller: getBooking starts for fetch all bookings");
        List<UserResponse> response = bookingService.getAllBooking(page,size);
        log.info("BookingController: getAllBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
