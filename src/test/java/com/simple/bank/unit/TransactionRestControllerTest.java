package com.simple.bank.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.bank.constant.TransactionTypeEnum;
import com.simple.bank.controller.TransactionRestController;
import com.simple.bank.dto.CreateAccountDto;
import com.simple.bank.dto.TransactionDto;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionRestController.class)
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void givenMissingTransactionForInput_whenMakingDeposit_thenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenAccountForInput_whenMakingDeposit_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\",\"accountNumber\": \"20241111\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenMissingAccountNumberForInput_whenMakingDeposit_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingDeposit_thenVerifyDeposit() throws Exception {
        TransactionDto transactionDto = new TransactionDto("12323", TransactionTypeEnum.DEPOSIT, 100.00);
        given(transactionService.deposit(null)).willReturn(null);
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingDepositWithGetRequest_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    private Optional<WsResponse> getExpectedResponse() {
        WsResponse response = new WsResponse();
        response.setMessage("Deposit Successful");
        response.setStatusCode(200);
        response.setData(null);
        return Optional.of(response);
    }

    @Test
    void givenMissingTransactionForInput_whenMakingWithdraw_thenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenAccountForInput_whenMakingWithdraw_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\",\"accountNumber\": \"20241111\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenMissingAccountNumberForInput_whenMakingWithdraw_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingWithdraw_thenVerifyDeposit() throws Exception {
        given(transactionService.withdraw(null)).willReturn(null);
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingWithDrawWithGetRequest_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    private Optional<WsResponse> getWithDrawExpectedResponse() {
        WsResponse response = new WsResponse();
        response.setMessage("WithDraw successful");
        response.setStatusCode(200);
        response.setData(null);
        return Optional.of(response);
    }

    @Test
    void givenMissingTransferDtoForInput_whenMakingTransfer_thenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenTransferDtoInput_whenMakingTransfer_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenMissingAccountNumberForInput_whenMakingTransfer_thenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingTransfer_thenVerifyDeposit() throws Exception {
        given(transactionService.withdraw(null)).willReturn(null);
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenTransactionForAccountDetail_whenMakingTransferWithGetRequest_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    private Optional<WsResponse> getTransferExpectedResponse() {
        WsResponse response = new WsResponse();
        response.setMessage("Transfer successful");
        response.setStatusCode(200);
        response.setData(null);
        return Optional.of(response);
    }

}