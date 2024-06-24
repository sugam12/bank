package com.simple.bank.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankResponseTest {

    @Test
    public void successResponseTest() {
        String message = "test success";
        int statusCode = HttpStatus.OK.value();
        ResponseEntity<WsResponse> responseResponseEntity = BankResponse.successResponse(message, statusCode);
        WsResponse wsResponse = responseResponseEntity.getBody();
        assertNotNull(wsResponse);
        assertEquals(message, wsResponse.getMessage());
        assertEquals(statusCode, wsResponse.getStatusCode());
    }

    @Test
    public void successResponseOverloadMethodTest() {
        String message = "test success";
        int statusCode = HttpStatus.OK.value();
        WsResponse wsResponse = new WsResponse();
        wsResponse.setStatusCode(statusCode);
        wsResponse.setMessage(message);
        ResponseEntity<WsResponse> responseResponseEntity = BankResponse.successResponse(message, statusCode, wsResponse);
        WsResponse returnedWsResponse = responseResponseEntity.getBody();
        assertNotNull(returnedWsResponse);
        assertEquals(message, returnedWsResponse.getMessage());
        assertEquals(statusCode, returnedWsResponse.getStatusCode());
        WsResponse dataWsResponse = (WsResponse) returnedWsResponse.getData();
        assertEquals(wsResponse.getStatusCode(), dataWsResponse.getStatusCode());
        assertEquals(wsResponse.getMessage(), dataWsResponse.getMessage());
    }

    @Test
    public void getResponseTest() {
        String message = "test success";
        int statusCode = HttpStatus.OK.value();
        WsResponse wsResponse = new WsResponse();
        wsResponse.setStatusCode(statusCode);
        wsResponse.setMessage(message);
        ResponseEntity<WsResponse> response = BankResponse.getResponse(wsResponse);
        WsResponse returnedResponse = response.getBody();
        assertNotNull(returnedResponse.getData());
        WsResponse wsResponseData = (WsResponse) returnedResponse.getData();
        assertNotNull(returnedResponse);
        assertNotNull(wsResponseData);
        assertEquals(wsResponse.getMessage(), wsResponseData.getMessage());
        assertEquals(wsResponse.getMessage(), wsResponseData.getMessage());
        assertEquals(wsResponse.getStatusCode(), wsResponseData.getStatusCode());
    }

    @Test
    public void failureResponseTest() {
        String message = "test failure";
        int statusCode = HttpStatus.OK.value();
        ResponseEntity<WsResponse> responseResponseEntity = BankResponse.failureResponse(message, statusCode);
        WsResponse returnedResponse = responseResponseEntity.getBody();
        assertNotNull(returnedResponse);
        assertEquals(message, returnedResponse.getMessage());
        assertEquals(statusCode, returnedResponse.getStatusCode());
    }
}
