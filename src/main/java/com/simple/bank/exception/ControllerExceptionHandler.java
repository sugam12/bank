package com.simple.bank.exception;

import com.simple.bank.response.WsResponse;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.simple.bank.constant.Constant.FAILURE_CUSTOMER_NOT_FOUND_MESSAGE;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WsResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        WsResponse message = new WsResponse(
                FAILURE_CUSTOMER_NOT_FOUND_MESSAGE,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return new ResponseEntity<WsResponse>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<WsResponse> customerNotFound(CustomerNotFoundException ex, WebRequest request) {
        WsResponse message = new WsResponse(
                FAILURE_CUSTOMER_NOT_FOUND_MESSAGE,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return new ResponseEntity<WsResponse>(message, HttpStatus.BAD_REQUEST);
    }
}
