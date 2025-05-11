package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Value;
import lombok.With;

import jakarta.validation.constraints.NotNull;

public interface AplicarDescontoPedidoUseCase {

    void handle(AplicarDescontoPedido command);

    @Value
    class AplicarDescontoPedido {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @With
        UUID id;

        @NotNull(message = "Desconto deve ser informado!")
        BigDecimal desconto;
    }
}
