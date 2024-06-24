package com.simple.bank.repository;

import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAddCustomerWithoutAddressAndContact() {
        Customer customer = new Customer();
        customer.setFirstName("Dummy");
        customer.setLastName("Dummy");
        customer.setCustomerNumber("414442343");
        Customer savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void testAddCustomerWithAddressAndContact() {
        Customer customer = new Customer();
        customer.setFirstName("Dummy");
        customer.setLastName("Dummy");
        customer.setCustomerNumber("4144423434");
        customer.setAddress(getAddressToSave());
        customer.setContact(getContactToSave());
        Customer savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer).isNotNull();
    }
    private Contact getContactToSave() {
        return Contact.builder()
                .homePhone("2333222")
                .workPhone("2332222")
                .emailId("sugamachr@gmail.com")
                .build();
    }

    private Address getAddressToSave() {
        return Address.builder()
                .state("WA")
                .zip("8877")
                .city("Perth")
                .country("Australia")
                .build();
    }


}
