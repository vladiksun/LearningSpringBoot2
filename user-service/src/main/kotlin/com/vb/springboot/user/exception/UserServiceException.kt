package com.vb.springboot.user.exception;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 3300122125227502449L;

    public UserServiceException(String message) {
        super(message);
    }
}
