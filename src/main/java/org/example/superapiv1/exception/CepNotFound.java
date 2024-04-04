package org.example.superapiv1.exception;

public class CepNotFound extends RuntimeException{
    public CepNotFound() {
        super("Cep incorreto" );
    }
}
