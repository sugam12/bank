package com.simple.bank.unit;

import com.simple.bank.controller.AccountRestController;
import com.simple.bank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenMissingAccountForInput_whenCreatingAccount_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void givenAccountForInput_whenCreatingAccount_thenVerifyIsOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                .content("{\"customerNumber\": \"5368\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

   /* @Test
    void givenAccountForInput_whenCreatingAccount_thenVerifyContentType() throws Exception {
        given(accountService.findByAccountNumber(null)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                        .content("{\"customerNumber\": \"536892\",\"accountNumber\": \"20241334\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
*/
    @Test
    public void givenAccountNumberForAccountDetail_whenCreatingAccount_thenVerifyAccount() throws Exception {
        given(accountService.findByAccountNumber(null)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/account")
                        .content("{\"accountNumber\": \"20241334\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
