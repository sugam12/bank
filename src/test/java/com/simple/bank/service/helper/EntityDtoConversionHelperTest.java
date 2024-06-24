package com.simple.bank.service.helper;

import com.simple.bank.dto.AddressDto;
import com.simple.bank.dto.ContactDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.dto.CustomerDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityDtoConversionHelperTest {

    @Autowired
    EntityDtoConversionHelper entityDtoConversionHelper;

    @Test
    public void convertCustomerEntityTest() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("test");
        customerDto.setLastName("test");
        customerDto.setMiddleName("test");
        customerDto.setCustomerNumber("4144423434L");
        customerDto.setAddress(getAddressDTO());
        customerDto.setContact(getContactDTO());
        Customer customer = entityDtoConversionHelper.convertCustomerEntity(customerDto);
        assertEquals(customer.getCustomerNumber(), customerDto.getCustomerNumber());
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getMiddleName(), customerDto.getMiddleName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getContact().getEmailId(), customerDto.getContact().getEmailId());
        assertEquals(customer.getContact().getWorkPhone(), customerDto.getContact().getWorkPhone());
        assertEquals(customer.getContact().getHomePhone(), customerDto.getContact().getHomePhone());
        assertEquals(customer.getAddress().getCity(), customerDto.getAddress().getCity());
        assertEquals(customer.getAddress().getZip(), customerDto.getAddress().getZip());
        assertEquals(customer.getAddress().getCountry(), customerDto.getAddress().getCountry());
        assertEquals(customer.getAddress().getState(), customerDto.getAddress().getState());
    }

    private Contact getContact() {
        return Contact.builder()
                .emailId("sugamachr@gmai.com")
                .workPhone("23423412")
                .homePhone("34234234")
                .build();
    }

    private Address getAddress() {
        return Address.builder()
                .zip("4565")
                .city("Perth")
                .state("Western Australia")
                .country("Australia")
                .build();
    }

    private ContactDto getContactDTO() {
      return ContactDto.builder()
                .emailId("sugamachr@gmai.com")
                .workPhone("23423412")
                .homePhone("34234234")
                .build();
    }

    private AddressDto getAddressDTO() {
        return AddressDto.builder()
                .zip("4565")
                .city("Perth")
                .state("Western Australia")
                .country("Australia")
                .build();
    }

    @Test
    public void convertToCustomerDtoTest() {
        Customer customer = new Customer();
        customer.setFirstName("test");
        customer.setLastName("test");
        customer.setMiddleName("test");
        customer.setCustomerNumber("4144423434");
        customer.setAddress(getAddress());
        customer.setContact(getContact());
        CustomerDto customerDto = entityDtoConversionHelper.convertToCustomerDto(customer);
        assertEquals(customerDto.getCustomerNumber(), customer.getCustomerNumber());
        assertEquals(customerDto.getFirstName(), customer.getFirstName());
        assertEquals(customerDto.getMiddleName(), customer.getMiddleName());
        assertEquals(customerDto.getLastName(), customer.getLastName());
        assertEquals(customerDto.getContact().getEmailId(), customer.getContact().getEmailId());
        assertEquals(customerDto.getContact().getWorkPhone(), customer.getContact().getWorkPhone());
        assertEquals(customerDto.getContact().getHomePhone(), customer.getContact().getHomePhone());
        assertEquals(customerDto.getAddress().getCity(), customer.getAddress().getCity());
        assertEquals(customerDto.getAddress().getZip(), customer.getAddress().getZip());
        assertEquals(customerDto.getAddress().getCountry(), customer.getAddress().getCountry());
        assertEquals(customerDto.getAddress().getState(), customer.getAddress().getState());
    }

    @Test
    public void convertToAccountEntityTest() {
        CreateAccountDto createAccountDto = new CreateAccountDto();
        createAccountDto.setAccountNumber("20245678");
        createAccountDto.setCustomerNumber("23467777");
        createAccountDto.setCurrentBalance(100.00);
        Account account = entityDtoConversionHelper.convertToAccountEntity(createAccountDto);
        assertEquals(createAccountDto.getCustomerNumber(), account.getCustomerNumber());
        assertEquals("SAVING", account.getAccountType());
    }

    @Test
    public void convertToAccountDtoTest() {
        Account createAccountDto = new Account();
        createAccountDto.setAccountNumber("20245678");
        createAccountDto.setCustomerNumber("23467777");
        createAccountDto.setCurrentBalance(100.00);
        CreateAccountDto account = entityDtoConversionHelper.convertToAccountDto(createAccountDto);
        assertEquals(createAccountDto.getCustomerNumber(), account.getCustomerNumber());
        assertEquals(createAccountDto.getAccountNumber(), account.getAccountNumber());

    }
}
