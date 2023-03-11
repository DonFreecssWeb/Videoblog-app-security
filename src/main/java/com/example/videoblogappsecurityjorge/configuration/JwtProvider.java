package com.example.videoblogappsecurityjorge.configuration;

import com.example.videoblogappsecurityjorge.shared.Constants;
import io.jsonwebtoken.*;
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

    public String generateToken(String username, String  apikey){
        Claims claims = Jwts.claims();
        claims.put("api-key",apikey);


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInSeconds * 1000))
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(signinKey), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUsernameFromToken(String token){
            return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(signinKey))
                    .build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJWT(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(signinKey).build().parseClaimsJws(token);
            return  ! claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtExceptionPersonal e){
            return false;
        }

    }

}


