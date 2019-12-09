package com.loneliness.client.dao;

public class DAOException extends Throwable{
    private String userMessage;
    public DAOException(String message,String userMessage) {
        super(userMessage);
        this.userMessage=userMessage;
    }

    public DAOException(String message, Throwable cause,String userMessage) {
        super(message, cause);
        this.userMessage=userMessage;
    }

    public DAOException(Throwable cause,String userMessage) {
        super(userMessage, cause);
        this.userMessage=userMessage;
    }
}
