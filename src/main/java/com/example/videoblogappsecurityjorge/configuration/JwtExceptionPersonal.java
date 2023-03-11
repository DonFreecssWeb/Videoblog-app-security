package com.example.videoblogappsecurityjorge.configuration;

import io.jsonwebtoken.JwtException;

public class JwtExceptionPersonal  extends RuntimeException {
    private int code;
    private String message;

    public JwtExceptionPersonal(String message, int code){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
