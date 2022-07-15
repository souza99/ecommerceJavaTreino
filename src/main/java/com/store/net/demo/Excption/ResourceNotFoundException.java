package com.store.net.demo.Excption;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
