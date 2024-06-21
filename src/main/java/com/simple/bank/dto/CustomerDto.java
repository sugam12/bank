package com.simple.bank.dto;

import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
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

    private AddressDto address;

    private ContactDto contact;
}
