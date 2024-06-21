package com.simple.bank.service;

import com.simple.bank.dto.CustomerDto;
import com.simple.bank.dto.BankResponse;
import com.simple.bank.dto.WsResponse;
import com.simple.bank.entity.Customer;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.service.helper.CustomerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerHelper customerHelper;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * CREATE CUSTOMER
     *
     * @param customerDto
     * @return
     */
    @Override
    public ResponseEntity<WsResponse> addCustomer(CustomerDto customerDto) {
        Customer customer = customerHelper.convertCustomerEntity(customerDto);
        customerRepository.save(customer);
        return BankResponse.successResponse("Customer registered successfully", HttpStatus.CREATED.value());
    }

    /**
     * Find all the Customer List wrapped in WsResponse
     * @return
     */
    @Override
    public ResponseEntity<WsResponse> findAll() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();

        customers.forEach(customer -> {
            customerDtoList.add(customerHelper.convertToCustomerDto(customer));
        });

        return BankResponse.getResponse(customerDtoList);
    }
}
