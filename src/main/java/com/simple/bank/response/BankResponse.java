package com.simple.bank.response;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.simple.bank.constant.Constant.FETCH_SUCCESSFUL;

@NoArgsConstructor
public class BankResponse {
    public static ResponseEntity<WsResponse> successResponse(String message, int statusCode) {
        WsResponse r = new WsResponse();
        r.setMessage(message);
        r.setStatusCode(statusCode);
        return ResponseEntity.status(statusCode).body(r);
    }

    public static ResponseEntity<WsResponse> successResponse(String message, int statusCode, Object object) {
        WsResponse r = new WsResponse();
        r.setMessage(message);
        r.setStatusCode(statusCode);
        r.setData(object);
        return ResponseEntity.status(statusCode).body(r);
    }

    public static ResponseEntity<WsResponse> getResponse(Object object) {
        WsResponse r = new WsResponse();
        r.setMessage(FETCH_SUCCESSFUL);
        r.setStatusCode(HttpStatus.OK.value());
        r.setData(object);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    public static ResponseEntity<WsResponse> failureResponse(String message, int statusCode) {
        WsResponse r = new WsResponse();
        r.setMessage(message);
        r.setStatusCode(statusCode);
        return ResponseEntity.status(statusCode).body(r);
    }
}
