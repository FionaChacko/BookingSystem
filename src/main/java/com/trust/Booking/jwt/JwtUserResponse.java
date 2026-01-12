package com.trust.Booking.jwt;


import com.trust.Booking.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserResponse {

    private JwtResponse jwtResponse;
    private UserResponse userResponse;

}