package com.api.chat.exec;

public class UserAlreadyException extends Exception {
    public UserAlreadyException(String message) {
        super(message);
    }
}
