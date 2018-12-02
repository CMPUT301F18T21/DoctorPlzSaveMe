package com.erikligai.doctorplzsaveme.Exceptions;

public class TooLongProblemTitleException extends Exception {
    public TooLongProblemTitleException(String message){
        super(message);
    }
}