package com.erikligai.doctorplzsaveme;

public class TooLongProblemDescException extends Exception{
    public TooLongProblemDescException(String message){
        super(message);
    }
}
