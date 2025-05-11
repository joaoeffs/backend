package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoCanceladoException;

public class PedidoStateStateCancelado implements PedidoState {

    @Override
    public Situacao fechar(Pedido pedido) {
        throw new PedidoCanceladoException("Não é possível fechar um pedido já cancelado");
    }

    @Override
    public Situacao cancelar(Pedido pedido) {
        throw new PedidoCanceladoException("Não é possível cancelar um pedido já cancelado");
    }
}
