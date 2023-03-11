package com.example.videoblogappsecurityjorge.shared;

public class Constants {
    //HTTP CODES
    public static final int HTTP_CODE_NOT_FOUND=404;
    public static final int HTTP_CODE_JWT_EXPIRED=512;

    //CODES
    public static final int CODE_WHEN_EXCEPTION_FETCHING_SECRET=513;
    public static final int CODE_WHEN_USER_ALREADY_EXISTS=514;
    public static final int CODE_WHEN_EXCEPTION_SAVING_USER=515;
    //GENERAL MESSAGES
    public static final String ERROR_MESSAGE_WHEN_INCORRECT_PASS="Password wrong";
    public static final String ERROR_MESSAGE_WHEN_INCORRECT_REQUEST="Error in the request";
    public static final String ERROR_MESSAGE_WHEN_CLIENTE_DOES_NOT_EXIST="Customer no exits";
    public static final String ERROR_MESSAGE_WHEN_CLIENTE_ALREADY_EXISTS="Customer already exists";
    public static final String ERROR_MESSAGE_WHEN_JWT_EXPIRED="Json Web Token has expired";
    public static final String SUCCESS_MESSAGE="Register success";

}
