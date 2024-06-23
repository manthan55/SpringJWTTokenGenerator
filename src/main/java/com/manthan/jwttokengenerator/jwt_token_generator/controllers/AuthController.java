package com.manthan.jwttokengenerator.jwt_token_generator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manthan.jwttokengenerator.jwt_token_generator.dtos.LoginRequestDTO;
import com.manthan.jwttokengenerator.jwt_token_generator.dtos.LoginResponseDTO;
import com.manthan.jwttokengenerator.jwt_token_generator.dtos.ValidateTokenRequestDTO;
import com.manthan.jwttokengenerator.jwt_token_generator.dtos.ValidateTokenResponseDTO;
import com.manthan.jwttokengenerator.jwt_token_generator.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/loginWithOTP")
    public ResponseEntity<LoginResponseDTO> loginWithOTP(@RequestBody LoginRequestDTO requestDTO){
        try{
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            String token = authService.loginWithOTP(requestDTO.getUserId(), requestDTO.getOtp());
            responseDTO.setToken(token);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);
        }
        catch(Exception ex){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Object> validateToken(@RequestBody ValidateTokenRequestDTO requestDTO){
        try{
            ValidateTokenResponseDTO responseDTO = new ValidateTokenResponseDTO();
            boolean isValid = authService.validateToken(requestDTO.getToken());
            responseDTO.setValid(isValid);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);
        }
        catch(Exception ex){
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
        }
    }
}
