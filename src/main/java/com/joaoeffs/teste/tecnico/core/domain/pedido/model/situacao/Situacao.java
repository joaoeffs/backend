package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;

public enum Situacao implements PedidoState {

    PENDENTE (new PedidoStateStatePendente()),
    FECHADO (new PedidoStateStateFechado()),
    CANCELADO (new PedidoStateStateCancelado());

    private final PedidoState rule;

    Situacao(PedidoState rule) {
        this.rule = rule;
    }

    @Override
    public Situacao fechar(Pedido pedido) {
        return rule.fechar(pedido);
    }

    @Override
    public Situacao cancelar(Pedido pedido) {
        return rule.cancelar(pedido);
    }
}
