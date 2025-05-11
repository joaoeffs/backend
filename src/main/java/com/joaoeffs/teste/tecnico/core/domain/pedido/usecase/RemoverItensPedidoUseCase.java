package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.util.Set;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface RemoverItensPedidoUseCase {

    void handle(RemoverItensPedido command);

    @Builder
    @Value
    class RemoverItensPedido {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @With
        UUID id;

        @Valid
        @NotNull(message = "Itens devem ser informados")
        Set<Itens> itens;
    }

    @Builder
    @Value
    class Itens {

        @NotNull(message = "ItemPedidoId deve ser informado! ")
        UUID itemPedido;
    }
}
