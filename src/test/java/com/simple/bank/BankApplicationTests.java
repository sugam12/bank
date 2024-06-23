package com.simple.bank;

import com.simple.bank.dto.CustomerDto;
import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.service.CustomerService;
import com.simple.bank.service.helper.EntityDtoConversionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    EntityDtoConversionHelper entityDtoConversionHelper;

    @MockBean
    CustomerRepository customerRepository;

    @Test()
    public void testFindAllCustomerUsingMock() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setFirstName("Test1");
            customer.setLastName("Test2");
            customer.setAddress(new Address(1L, "Perth", "Western Australia", "0612", "Australia"));
            customer.setContact(new Contact(1L, "sugamachr@gmail.com", "0841987879", "234568678"));
            customerList.add(customer);
        }

        when(customerRepository.findAll())
                .thenReturn(customerList);
        List<CustomerDto> customerDtoList = (List<CustomerDto>) customerService.findAll().getBody().getData();
        assertEquals(10, customerDtoList.size());
        assertNotNull(customerDtoList);
    }
    @Test
    public void testFindAllCustomerWithEmptyListUsingMock() {
        List<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll())
                .thenReturn(customerList);
        List<CustomerDto> customerDtoList = (List<CustomerDto>) customerService.findAll().getBody().getData();
        assertEquals(0, customerDtoList.size());
    }

    @Test
    public void saveCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Test1");
        customer.setLastName("Test2");
        customer.setAddress(new Address(1L,"Perth", "Western Australia", "0612", "Australia"));
        customer.setContact(new Contact(1L,"sugamachr@gmail.com", "0841987879", "234568678"));
        CustomerDto customerDto = entityDtoConversionHelper.convertToCustomerDto(customer);
        when(customerRepository.save(customer))
            .thenReturn(customer);
        customerService.addCustomer(customerDto);
        assertEquals(customer, customer);
    }

}
