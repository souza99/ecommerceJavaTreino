package com.store.net.demo.Excption;

public class ResourceAlreadyExistsException extends Exception{

    public ResourceAlreadyExistsException(){}

    public ResourceAlreadyExistsException(String message){
        super(message);
    }


}
