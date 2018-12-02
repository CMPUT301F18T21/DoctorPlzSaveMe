package com.erikligai.doctorplzsaveme.Exceptions;

/**
 * class for too long of problem description exception (so we can catch it specifically)
 */
public class TooLongProblemDescException extends Exception{
    public TooLongProblemDescException(String message){
        super(message);
    }
}
