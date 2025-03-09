package com.gereciador.estabelecimento.exceptions;

public class EstabelecimentoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EstabelecimentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EstabelecimentoException(String message) {
        super(message);
    }
}
