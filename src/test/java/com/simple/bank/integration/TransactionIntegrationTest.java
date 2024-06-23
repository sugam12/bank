package com.simple.bank.integration;

import com.simple.bank.constant.TransactionTypeEnum;
import com.simple.bank.controller.TransactionRestController;
import com.simple.bank.dto.TransactionDto;
import com.simple.bank.entity.Account;
import com.simple.bank.repository.AccountRepository;
import com.simple.bank.response.WsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.simple.bank.constant.Constant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class TransactionIntegrationTest {

    @Autowired
    private TransactionRestController transactionRestController;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void givenTransactionDetails_whenDepositAmount_thenVerifyDepositSuccess() {
        // given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountNumber("1223232");
        transactionDto.setAmount(100.00);
        transactionDto.setTransactionTypeEnum(TransactionTypeEnum.DEPOSIT);

        Account account = new Account();
        account.setAccountType("SAVING");
        account.setCustomerNumber(234234L);
        account.setAccountNumber(transactionDto.getAccountNumber());
        account.setCurrentBalance(120.00);
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepository.findByAccountNumber(transactionDto.getAccountNumber()))
                .thenReturn(accountOptional);

        // when
        var body = transactionRestController.deposit(transactionDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(DEPOSIT_SUCCESS_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getData()).isNull();
    }

    @Test
    void givenTransactionDetails_whenDepositAmount_thenVerifyDepositFail() {
        // given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountNumber("1223232");
        transactionDto.setAmount(100.00);
        transactionDto.setTransactionTypeEnum(TransactionTypeEnum.DEPOSIT);


        // when
        var body = transactionRestController.deposit(transactionDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transactionDto.getAccountNumber());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getData()).isNull();
    }

    @Test
    void givenTransactionDetails_whenWithDrawAmount_thenVerifyWithDrawSuccess() {
        // given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountNumber("1223232");
        transactionDto.setAmount(100.00);
        transactionDto.setTransactionTypeEnum(TransactionTypeEnum.DEPOSIT);

        Account account = new Account();
        account.setAccountType("SAVING");
        account.setCustomerNumber(234234L);
        account.setAccountNumber(transactionDto.getAccountNumber());
        account.setCurrentBalance(120.00);
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepository.findByAccountNumber(transactionDto.getAccountNumber()))
                .thenReturn(accountOptional);
        // when
        var body = transactionRestController.withdraw(transactionDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(WITHDRAW_SUCCESS_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void givenTransactionDetails_whenWithDrawAmount_thenVerifyWithDrawFail() {
        // given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountNumber("1223232");
        transactionDto.setAmount(100.00);
        transactionDto.setTransactionTypeEnum(TransactionTypeEnum.WITHDRAW);

        // when
        var body = transactionRestController.withdraw(transactionDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(FAILURE_ACCOUNT_NOT_FOUND_MESSAGE + transactionDto.getAccountNumber());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenTransactionDetails_whenWithDrawAmount_thenVerifyInsuffientBalance() {
        // given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountNumber("1223232");
        transactionDto.setAmount(120.00);
        transactionDto.setTransactionTypeEnum(TransactionTypeEnum.DEPOSIT);

        Account account = new Account();
        account.setAccountType("SAVING");
        account.setCustomerNumber(234234L);
        account.setAccountNumber(transactionDto.getAccountNumber());
        account.setCurrentBalance(100.00);
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepository.findByAccountNumber(transactionDto.getAccountNumber()))
                .thenReturn(accountOptional);
        // when
        var body = transactionRestController.withdraw(transactionDto).getBody();

        // then
        var response = (WsResponse) body;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo(INSUFFICIENT_BALANCE_MESSAGE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
