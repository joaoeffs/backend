package com.joaoeffs.teste.tecnico.core.domain.pedido.model;

import java.util.function.Predicate;

import com.joaoeffs.teste.tecnico.infra.exception.NumeroPedidoException;

import lombok.Getter;

@Getter
public class PedidoBuilder {

    private String cliente;

    private Integer numeroPedido;

    private Predicate<Integer> numeroPedidoConstraint;

    public PedidoBuilder cliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder numeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
        return this;
    }

    public PedidoBuilder numeroPedidoConstraint(Predicate<Integer> numeroPedidoConstraint) {
        this.numeroPedidoConstraint = numeroPedidoConstraint;
        return this;
    }

    public Pedido build() {
        if (numeroPedidoConstraint.test(numeroPedido))
            throw new NumeroPedidoException("Número do pedido informado já existe!");

        return new Pedido(this);
    }
}
