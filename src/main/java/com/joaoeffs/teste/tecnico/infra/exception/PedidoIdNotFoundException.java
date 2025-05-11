package com.joaoeffs.teste.tecnico.infra.exception;

public class PedidoIdNotFoundException extends RuntimeException {

    public PedidoIdNotFoundException(String message) {
        super(message);
    }
}
