package com.simple.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

    private String firstName;

    private String middleName;

    private String lastName;

    private Long customerNumber;

    private AddressDto address;

    private ContactDto contact;
}
