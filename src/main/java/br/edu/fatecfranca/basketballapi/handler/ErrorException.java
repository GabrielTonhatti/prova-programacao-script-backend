package br.edu.fatecfranca.basketballapi.handler;

import lombok.Data;

@Data
public class ErrorException extends RuntimeException {

    private String field;
    private String message;

    public ErrorException(String message) {
        super(message);
        this.message = message;
    }
}
