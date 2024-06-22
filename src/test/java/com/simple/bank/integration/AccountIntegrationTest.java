package com.simple.bank.integration;

import com.simple.bank.controller.AccountController;
import com.simple.bank.dto.*;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import com.simple.bank.exception.ControllerExceptionHandler;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.response.WsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static com.simple.bank.constant.Constant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class AccountIntegrationTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void givenAccountDetails_whenCreateAccount_thenVerifyAccount() throws CustomerNotFoundException {
        // given
        var accountDto = new CreateAccountDto();
        accountDto.setAccountNumber("20243435");
        accountDto.setCurrentBalance(0.0);
        accountDto.setCustomerNumber(12323L);

        Customer customer = new Customer();
        customer.setFirstName("Test1");
        customer.setLastName("Test2");
        customer.setAddress(new Address(1L, "Perth", "Western Australia", "0612", "Australia"));
        customer.setContact(new Contact(1L, "sugamachr@gmail.com", "0841987879", "234568678"));
        Optional<Customer> customerOptional = Optional.of(customer);
        when(customerRepository.findByCustomerNumber(12323L)).thenReturn(customerOptional);

        // when
        var body = accountController.createAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(ACCOUNT_SUCCESS_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getData()).isNotNull();
        var data = (CreateAccountDto) ((WsResponse) body).getData();
        // assertThat(data.getAccountNumber()).isEqualTo(20243435);
        assertThat(data.getCustomerNumber()).isEqualTo(12323L);
        assertThat(data.getCurrentBalance()).isEqualTo(0.0);
    }

    @Test
    void givenAccountDetails_whenCreatingAccount_thenVerifyAccountFail() throws CustomerNotFoundException {
        // given
        var accountDto = new CreateAccountDto();
        accountDto.setAccountNumber("20243435");
        accountDto.setCurrentBalance(0.0);
        accountDto.setCustomerNumber(12323L);

        // when
        var body = accountController.createAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenAccountNumber_whenGettingAccountDetails_thenVerifyAccountDetails() throws CustomerNotFoundException {
        // given
        var accountDto = new AccountDto();
        accountDto.setAccountNumber("2024454566");
        Account account = new Account();
        account.setAccountType("SAVING");
        account.setCustomerNumber(234234L);
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setCurrentBalance(120.00);
        Optional<Account> accountOptional = Optional.of(account);
        when(accountRepository.findByAccountNumber(accountDto.getAccountNumber())).thenReturn(accountOptional);
        // when
        var body = accountController.getAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Fetch Successfully");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getData()).isNotNull();
        var data = (CreateAccountDto) ((WsResponse) body).getData();
        assertThat(data.getAccountNumber()).isEqualTo(account.getAccountNumber());
        assertThat(data.getCustomerNumber()).isEqualTo(234234L);
        assertThat(data.getCurrentBalance()).isEqualTo(120.00);
    }

    @Test
    void givenAccountNumber_whenGettingAccountDetails_thenVerifyFailingAccountDetails() throws CustomerNotFoundException {
        // given
        var accountDto = new AccountDto();
        accountDto.setAccountNumber("2024454566");
        // when

       /* when(accountRepository.findByAccountNumber(accountDto.getAccountNumber()))
                .thenReturn();*/
        assertThrows(CustomerNotFoundException.class, () -> accountController.getAccount(accountDto));
        // var body = accountController.getAccount(accountDto).getBody();

        // then
        /*var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FAILURE_ACCOUNT_NOT_FOUND_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());*/

    }
}
