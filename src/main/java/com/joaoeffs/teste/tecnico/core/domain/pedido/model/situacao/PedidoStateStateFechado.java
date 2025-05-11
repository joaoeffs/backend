package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoFechadoException;

public class PedidoStateStateFechado implements PedidoState {

    @Override
    public Situacao fechar(Pedido pedido) {
        throw new PedidoFechadoException("Não é possível fechar um pedido já fechado");
    }

    @Override
    public Situacao cancelar(Pedido pedido) {
        throw new PedidoFechadoException("Não é possível cancelar um pedido já fechado");
    }
}
