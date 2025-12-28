package com.trust.Booking.service;

import com.trust.Booking.constants.AppConstants;
import com.trust.Booking.exception.BusinessException;
import com.trust.Booking.mapper.UserMapper;
import com.trust.Booking.model.Register;
import com.trust.Booking.repository.BookingRepository;
import com.trust.Booking.request.UserRequest;
import com.trust.Booking.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

   private final UserMapper mapper;

    public BookingServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserResponse saveBook(UserRequest request) {
        log.info("BookingService: save Book starts with request {}",request);
        isValidate(request);
        Register register = new Register();
        register.setBookingTime(LocalDateTime.now());
        register.setName(request.getName());
        register.setAddress(request.getAddress());
        register.setActive(true);
        Register saved = bookingRepository.save(register);
        UserResponse response = mapper.registerToResponse(saved);
        log.info("BookingServiceImpl: save Booking ends");
        return response;

    }

    private void isValidate(UserRequest request) {
        log.info("BookingService: isValidate method starts");
        if(ObjectUtils.isEmpty(request.getName())){
            throw new BusinessException(AppConstants.IS_NAME_EMPTY);
        }
        if(ObjectUtils.isEmpty(request.getAddress())){
            throw new BusinessException(AppConstants.IS_ADDRESS_EMPTY);
        }

    }

    @Override
    public UserResponse updateBook(UserRequest request) {
        log.info("BookingServiceImpl: updateBook starts");
        if(ObjectUtils.isEmpty(request.getId())){
            throw new BusinessException(AppConstants.IS_ID_EMPTY);
        }
        isValidate(request);
        Optional<Register> userexists = bookingRepository.findById(request.getId());
        Register user = new Register();
        Register updated = new Register();
        UserResponse response = new UserResponse();
        if((userexists.isPresent())){
            user = userexists.get();
            user.setAddress(request.getAddress());
            user.setName(request.getName());
            user.setId(request.getId());
           updated =  bookingRepository.save(user);
           response.setAddress(updated.getAddress());
           response.setName(updated.getName());
           response.setId(updated.getId());
        }
        else{
            throw new BusinessException(AppConstants.USER_NOT_EXISTS);
        }
        log.info("BookingServiceImpl: updateBook ends");
        return response;
    }

    @Override
    public String deleteBook(int id) {
        Optional<Register> userexists = bookingRepository.findById(id);
        Register user = new Register();
        UserResponse response = new UserResponse();
        if((userexists.isPresent())){
            bookingRepository.deleteById(id);
            return "successfully deleted";
        }
        return "already deleted";
    }

    @Override
    public UserResponse getBooking(int id) {
        log.info("BookingServiceImpl: getBookingById starts of id {}", id);
        Optional<Register> userExists = bookingRepository.findById(id);
        Register user = new Register();
        UserResponse response = new UserResponse();
        if((userExists.isPresent())){
            user = userExists.get();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setAddress(user.getAddress());
        }
        else{
            throw new BusinessException(AppConstants.USER_NOT_EXISTS);
        }
        log.info("BookingServiceImpl: getBookingById ends");
        return response;
    }

    @Override
    public List<UserResponse> getAllBooking() {
        log.info("BookingService getAllBooking method starts");
        List<Register> list = bookingRepository.findAll();
        return list.stream().map(this::convertToUserResponse).collect(Collectors.toList());
    }

    private UserResponse convertToUserResponse(Register register) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(register.getId());
        userResponse.setName(register.getName());
        userResponse.setAddress(register.getAddress());
        return userResponse;
    }

}
