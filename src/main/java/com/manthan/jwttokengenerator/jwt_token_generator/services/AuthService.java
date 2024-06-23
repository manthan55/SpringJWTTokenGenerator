package com.manthan.jwttokengenerator.jwt_token_generator.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manthan.jwttokengenerator.jwt_token_generator.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

    // ideally you will fetch data from a real database via repositories
    // but in this sample using a hashmap as data store
    private Map<Long, User> users;

    @Autowired
    private SecretKey secretKey;

    // this key is used to sign the tokens
    // you should keep it somepalce safe (instead of hardcoding here)
    // like in Azure KeyVault or AWS SecretMaager
    private final String SECRET_KEY = "super_secret_key"; 

    public AuthService() {
        users = new HashMap<>();

        insertDummyUsers();
    }

    private void insertDummyUsers(){
        User u1 = new User();
        u1.setId(1L);
        u1.setUsername("manthan");

        User u2 = new User();
        u2.setId(2L);
        u2.setUsername("laksh");

        users.put(u1.getId(), u1);
        users.put(u2.getId(), u2);
    }

    public String loginWithOTP(Long userId, String otp){
        // perform OTP validation here

        // again - fetch this from a real database via repositories
        User user = users.get(userId);

        Map<String,Object> jwtData = new HashMap<>();
        jwtData.put("id",user.getId());
        jwtData.put("username",user.getUsername());
        long nowInMillis = System.currentTimeMillis();
        jwtData.put("expiryTime",new Date(nowInMillis+100000000));
        jwtData.put("createdAt",new Date(nowInMillis));


        String token = Jwts
                .builder()
                .claims(jwtData)
                .signWith(secretKey)
//                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
        // perform OTP validation here

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        System.out.println(claims);

        long nowInMillis = System.currentTimeMillis();
        long tokenExpiry = (Long)claims.get("expiryTime");

        if(nowInMillis > tokenExpiry) {
            System.out.println("Token has expired");
            return false;
        }

        return true;
    }
}
