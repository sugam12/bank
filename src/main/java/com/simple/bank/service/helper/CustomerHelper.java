package com.simple.bank.service.helper;

import com.simple.bank.dto.AddressDto;
import com.simple.bank.dto.ContactDto;
import com.simple.bank.dto.CustomerDto;
import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerHelper {
    public Customer convertCustomerEntity(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .middleName(customerDto.getMiddleName())
                .lastName(customerDto.getLastName())
                .contact(convertToContactEntity(customerDto.getContact()))
                .address(convertToAddressEntity(customerDto.getAddress()))
                .build();
    }

    private Address convertToAddressEntity(AddressDto addressDto) {
        return Address.builder()
                .zip(addressDto.getZip())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .build();
    }

    private Contact convertToContactEntity(ContactDto contactDto) {
        return Contact.builder()
                .emailId(contactDto.getEmailId())
                .workPhone(contactDto.getWorkPhone())
                .homePhone(contactDto.getHomePhone())
                .build();
    }

    public CustomerDto convertToCustomerDto(Customer customer) {

        return CustomerDto.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .contact(convertToContactDTO(customer.getContact()))
                .address(convertToAddressDTO(customer.getAddress()))
                .build();
    }

    private AddressDto convertToAddressDTO(Address address) {
       return AddressDto.builder()
                .zip(address.getZip())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    private ContactDto convertToContactDTO(Contact contact) {
        return ContactDto.builder()
                .emailId(contact.getEmailId())
                .workPhone(contact.getWorkPhone())
                .homePhone(contact.getHomePhone())
                .build();
    }
}