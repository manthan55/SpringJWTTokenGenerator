package com.manthan.jwttokengenerator.jwt_token_generator.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private long expiry;
}
