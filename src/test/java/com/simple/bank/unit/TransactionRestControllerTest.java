package com.simple.bank.unit;

import com.simple.bank.constant.TransactionTypeEnum;
import com.simple.bank.controller.TransactionRestController;
import com.simple.bank.dto.TransactionDto;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionRestController.class)
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void givenMissingTransactionForInputWhenMakingDepositThenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAccountForInputWhenMakingDepositThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\",\"accountNumber\": \"20241111\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenMissingAccountNumberForInputWhenMakingDepositThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingDepositThenVerifyDeposit() throws Exception {
        TransactionDto transactionDto = new TransactionDto("12323", TransactionTypeEnum.DEPOSIT, 100.00);
        given(transactionService.deposit(null)).willReturn(null);
        mvc.perform(post("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingDepositWithGetRequestThenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/deposit")
                        .content("{\"transactionTypeEnum\": \"DEPOSIT\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenMissingTransactionForInputWhenMakingWithdrawThenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAccountForInputWhenMakingWithdrawThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\",\"accountNumber\": \"20241111\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenMissingAccountNumberForInputWhenMakingWithdrawThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\",\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingWithdrawThenVerifyDeposit() throws Exception {
        given(transactionService.withdraw(null)).willReturn(null);
        mvc.perform(post("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingWithDrawWithGetRequestThenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/withdraw")
                        .content("{\"transactionTypeEnum\": \"WITHDRAW\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenMissingTransferDtoForInputWhenMakingTransferThenVerifyBadRequest() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenTransferDtoInputWhenMakingTransferThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenMissingAccountNumberForInputWhenMakingTransferThenVerifyOk() throws Exception {
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingTransferThenVerifyDeposit() throws Exception {
        given(transactionService.withdraw(null)).willReturn(null);
        mvc.perform(post("/api/v1/transfer")
                        .content("{\"fromAccountNumber\":{\"accountNumber\": \"20241111\"}, \"toAccountNumber\":{\"accountNumber\": \"20241112\"},\"amount\": \"100.00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTransactionForAccountDetailWhenMakingTransferWithGetRequestThenVerifyBadRequest() throws Exception {
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