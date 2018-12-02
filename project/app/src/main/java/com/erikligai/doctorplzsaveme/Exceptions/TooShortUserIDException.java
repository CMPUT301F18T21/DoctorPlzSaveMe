package com.erikligai.doctorplzsaveme.Exceptions;

public class TooShortUserIDException extends Exception {
    public TooShortUserIDException(String message){
        super(message);
    }
}
