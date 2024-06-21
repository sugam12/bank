package com.simple.bank.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
public class BankResponse {
    public static ResponseEntity<WsResponse> successResponse(String message, int status) {
        WsResponse r = new WsResponse();
        r.setMessage(message);
        r.setStatusCode(status);
        return ResponseEntity.status(status).body(r);
    }

    public static ResponseEntity<WsResponse> getResponse(Object object) {
        WsResponse r = new WsResponse();
        r.setMessage("Fetch Successfully");
        r.setStatusCode(HttpStatus.OK.value());
        r.setObject(object);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
}
