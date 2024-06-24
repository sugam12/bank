package com.simple.bank.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class WsResponse {
    private String message;
    private int statusCode;
    private Object data;
}
