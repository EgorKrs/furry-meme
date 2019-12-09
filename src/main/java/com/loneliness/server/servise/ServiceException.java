package com.loneliness.server.servise;

public class ServiceException extends Throwable{
    private String clientMessage;


    public ServiceException(String message,String clientMessage) {
        super(message);
        this.clientMessage = clientMessage;
    }

    public ServiceException(Throwable cause,String clientMessage) {
        super(clientMessage,cause);
        this.clientMessage = clientMessage;

    }


    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message,cause);
        this.clientMessage = message;
    }


    @Override
    public String getMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }


}
