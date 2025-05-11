package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;

public interface PedidoState {

    Situacao fechar(Pedido pedido);

    Situacao cancelar(Pedido pedido);
}
