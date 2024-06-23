package com.simple.bank.integration;

import com.simple.bank.controller.AccountRestController;
import com.simple.bank.dto.AccountDto;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.entity.Account;
import com.simple.bank.entity.Address;
import com.simple.bank.entity.Contact;
import com.simple.bank.entity.Customer;
import com.simple.bank.exception.CustomerNotFoundException;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.repository.CustomerRepository;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.helper.EntityDtoConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.simple.bank.constant.Constant.FAILURE_CUSTOMER_NOT_FOUND_MESSAGE;
import static com.simple.bank.constant.Constant.FETCH_SUCCESSFUL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class AccountIntegrationTest {

    @Autowired
    private AccountRestController accountRestController;

    @Autowired
    EntityDtoConversionHelper entityDtoConversionHelper;

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
        var body = accountRestController.createAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FETCH_SUCCESSFUL);
        assertThat(response.getStatusCode()).isEqualTo(200);
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
        var body = accountRestController.createAccount(accountDto).getBody();

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
        var body = accountRestController.getAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FETCH_SUCCESSFUL);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getData()).isNotNull();
        var data = (CreateAccountDto) ((WsResponse) body).getData();
        assertThat(data.getAccountNumber()).isEqualTo(account.getAccountNumber());
        assertThat(data.getCustomerNumber()).isEqualTo(234234L);
        assertThat(data.getCurrentBalance()).isEqualTo(120.00);
    }

    @Test
    void givenAccountNumber_whenGettingAccountDetails_thenVerifyOkAccountDetails() throws CustomerNotFoundException {
        // given
        var accountDto = new AccountDto();
        accountDto.setAccountNumber("2024454566");
        // when

        Account account = new Account();
        account.setAccountId(211233L);
        account.setAccountNumber("234234");
        account.setAccountType("Savings");


        Optional<Account> expectedAccountNumber = Optional.of(account);
        when(accountRepository.findByAccountNumber(accountDto.getAccountNumber()))
                .thenReturn(expectedAccountNumber);
        // assertThrows(CustomerNotFoundException.class, () -> accountRestController.getAccount(accountDto));
        var body = accountRestController.getAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;
        CreateAccountDto expectedData = entityDtoConversionHelper.convertToAccountDto(account);
        assertThat(response).isNotNull();
        CreateAccountDto actualData = (CreateAccountDto) response.getData();
        assertThat(response.getMessage()).isEqualTo(FETCH_SUCCESSFUL);
        assertEquals(expectedData, actualData);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void givenAccountNumber_whenGettingAccountDetails_thenVerifyFailingAccountDetails() throws CustomerNotFoundException {
        // given
        var accountDto = new AccountDto();
        accountDto.setAccountNumber("2024454566");
        // when

        var body = accountRestController.getAccount(accountDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FAILURE_CUSTOMER_NOT_FOUND_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
