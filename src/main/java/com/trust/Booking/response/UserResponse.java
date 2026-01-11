package com.trust.Booking.response;

import com.trust.Booking.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private Address address;

}
