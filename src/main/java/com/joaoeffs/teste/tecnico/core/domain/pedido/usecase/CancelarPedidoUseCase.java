package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.util.UUID;

import lombok.Value;

public interface CancelarPedidoUseCase {

    void handle(CancelarPedido command);

    @Value(staticConstructor = "from")
    class CancelarPedido {

        UUID id;
    }
}
