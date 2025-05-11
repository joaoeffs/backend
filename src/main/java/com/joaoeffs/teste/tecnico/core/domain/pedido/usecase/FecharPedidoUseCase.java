package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.util.UUID;

import lombok.Value;

public interface FecharPedidoUseCase {

    void handle(FecharPedido command);

    @Value(staticConstructor = "from")
    class FecharPedido {

        UUID pedido;
    }
}
