package com.api.chat.exec;

public class NotAuthorizedUser extends Exception {
    public NotAuthorizedUser(String s) {
        super(s);
    }
}
