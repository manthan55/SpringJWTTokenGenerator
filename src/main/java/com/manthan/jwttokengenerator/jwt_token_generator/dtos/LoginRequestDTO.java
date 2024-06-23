package com.manthan.jwttokengenerator.jwt_token_generator.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private Long userId;
    private String otp;
}
