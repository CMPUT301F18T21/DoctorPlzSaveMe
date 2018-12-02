package com.erikligai.doctorplzsaveme.Exceptions;

/**
 * class for too long of problem title exception (so we can catch it specifically)
 */
public class TooLongProblemTitleException extends Exception {
    public TooLongProblemTitleException(String message){
        super(message);
    }
}