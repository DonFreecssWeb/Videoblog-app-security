package com.example.videoblogappsecurityjorge.secrets;

public class SecretException extends RuntimeException {
    private final int code;
    SecretException(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
