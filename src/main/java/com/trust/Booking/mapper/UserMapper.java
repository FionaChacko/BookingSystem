package com.trust.Booking.mapper;

import com.trust.Booking.model.Register;
import com.trust.Booking.request.UserRequest;
import com.trust.Booking.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse registerToResponse(Register register);
    Register requestToRegister(UserRequest userRequest);
}
