package com.erikligai.doctorplzsaveme.Exceptions;

/**
 * class for too short of user id exception (so we can catch it specifically)
 */
public class TooShortUserIDException extends Exception {
    public TooShortUserIDException(String message){
        super(message);
    }
}
