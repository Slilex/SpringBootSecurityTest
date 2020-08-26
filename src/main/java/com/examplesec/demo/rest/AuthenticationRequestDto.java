package com.examplesec.demo.rest;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
