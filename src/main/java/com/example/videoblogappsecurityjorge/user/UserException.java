package com.example.videoblogappsecurityjorge.user;

public class UserException extends RuntimeException {

    private int code;
    public UserException(int codeResponse){
    this.code = codeResponse;
    }

    public int getCode() {
        return code;
    }
}
