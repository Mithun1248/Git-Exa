package com.api.chat.execeptions;

public class NotAuthorizedUser extends Exception {
    public NotAuthorizedUser(String s) {
        super(s);
    }
}
