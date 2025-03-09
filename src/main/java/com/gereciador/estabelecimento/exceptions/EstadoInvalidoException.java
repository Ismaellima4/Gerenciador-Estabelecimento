package com.gereciador.estabelecimento.exceptions;

public class EstadoInvalidoException extends EstabelecimentoException{


    public EstadoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EstadoInvalidoException(String message) {
        super(message);
    }
}
