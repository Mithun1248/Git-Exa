package com.api.chat.execeptions;

public class UserAlreadyException extends Exception {
    public UserAlreadyException(String message) {
        super(message);
    }
}
