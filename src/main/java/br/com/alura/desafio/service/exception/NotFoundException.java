package br.com.alura.desafio.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super("Resource not found.");
    }
}
