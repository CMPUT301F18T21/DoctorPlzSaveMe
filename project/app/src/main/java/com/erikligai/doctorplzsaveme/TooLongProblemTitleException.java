package com.erikligai.doctorplzsaveme;

public class TooLongProblemTitleException extends Exception {
    public TooLongProblemTitleException(String message){
        super(message);
    }
}