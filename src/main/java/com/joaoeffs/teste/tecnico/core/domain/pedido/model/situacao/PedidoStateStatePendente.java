package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;

public class PedidoStateStatePendente implements PedidoState {

    @Override
    public Situacao fechar(Pedido pedido) {
        return Situacao.FECHADO;
    }

    @Override
    public Situacao cancelar(Pedido pedido) {
        return Situacao.CANCELADO;
    }
}
