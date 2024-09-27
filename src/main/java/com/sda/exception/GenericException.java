package com.sda.exception;

public class GenericException extends RuntimeException{
    private GenericException(String message){
        super(message);
    }
    public static GenericException existsBy(String field, String nid) {
        String message = String.format("User with %s %s exists", field, nid);
        return new GenericException(message);
    }
}
