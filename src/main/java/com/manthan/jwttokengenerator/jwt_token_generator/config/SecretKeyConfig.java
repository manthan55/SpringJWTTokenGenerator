package com.manthan.jwttokengenerator.jwt_token_generator.config;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

@Configuration
public class SecretKeyConfig {
    @Bean
    public SecretKey secret() {
        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secret = algorithm.key().build();
        return secret;
    }
}
