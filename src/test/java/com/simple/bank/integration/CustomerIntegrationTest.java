package com.simple.bank.integration;

import com.simple.bank.controller.CustomerRestController;
import com.simple.bank.dto.AddressDto;
import com.simple.bank.dto.ContactDto;
import com.simple.bank.dto.CustomerDto;
import com.simple.bank.response.WsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.simple.bank.constant.Constant.FETCH_SUCCESSFUL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class CustomerIntegrationTest {

    @Autowired
    private CustomerRestController customerRestController;

    @Test
    public void givenCustomerDetailsWhenInsertingCustomerThenVerifyCustomer() {
        // given
        var customer = new CustomerDto();
        customer.setFirstName("Dummy");
        customer.setLastName("Dummy");
        customer.setAddress(new AddressDto("Perth", "Western Australia", "0612", "Australia"));
        customer.setContact(new ContactDto("sugamachr@gmail.com", "0841987879", "234568678"));

        // when
        var body = customerRestController.addCustomer(customer).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Customer registered successfully");
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void givenCustomerDetailsWhenGettingCustomerThenVerifyCustomer() {
        // given
        var customer = new CustomerDto();
        customer.setFirstName("Dummy");
        customer.setLastName("Dummy");
        customer.setAddress(new AddressDto("Perth", "Western Australia", "0612", "Australia"));
        customer.setContact(new ContactDto("sugamachr@gmail.com", "0841987879", "234568678"));

        // when
        var body = customerRestController.getCustomer().getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FETCH_SUCCESSFUL);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
