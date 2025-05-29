package com.example.demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secretKey = "lFchaOCX4PhHb9oleMJJiWaVcMZou3gD4fGICGvdpp51zwNTnOtnb8BnQY8Bsomg";

    public String extractUsername (String jwt){
        return extractClaim(jwt,Claims::getSubject);
    }

    public <T>  T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT token is missing");
        }
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*60*60*30))
                .and()
                .signWith(getSignInKey())
                .compact();

    }



    public boolean isTokenValidated(String token , UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
