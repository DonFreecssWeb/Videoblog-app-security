package com.example.videoblogappsecurityjorge.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    private final byte [] signinKey;
    private final String  SECRET_KEY ="secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";
    private final long tokenValidityInSeconds;

    public JwtProvider(){
        this.signinKey = Decoders.BASE64.decode(SECRET_KEY);
        tokenValidityInSeconds = 60;
    }

    public String generateToken(String username){

        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInSeconds * 1000))
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(signinKey), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(signinKey))
                .build().parseClaimsJws(token).getBody().getSubject();
    }

}
