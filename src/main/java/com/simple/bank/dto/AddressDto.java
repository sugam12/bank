package com.simple.bank.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDto {

    private String city;

    private String state;

    private String zip;

    private String country;
}
