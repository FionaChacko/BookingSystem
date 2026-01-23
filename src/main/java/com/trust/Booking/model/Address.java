package com.trust.Booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String addressLine1;
    private String addressLine2;
    private String place;
    private String city;
    private String state;
    @NotBlank(message = "postcode is mandatory")
    private Integer postcode;
}
