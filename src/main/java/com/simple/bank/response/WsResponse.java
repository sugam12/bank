package com.simple.bank.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WsResponse {
    private String message;
    private int statusCode;
    private Object data;
}
