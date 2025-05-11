package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.util.Set;
import java.util.UUID;

import com.joaoeffs.teste.tecnico.validation.quantidade.QuantidadePositiva;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface AdicionarItensPedidoUseCase {

    void handle(AdicionarItensPedido command);

    @Builder
    @Value(staticConstructor = "from")
    class AdicionarItensPedido {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @With
        UUID id;

        @Valid
        @NotNull(message = "Itens devem ser informados")
        Set<Item> itens;
    }

    @Builder
    @Value
    class Item {

        @NotNull(message = "ProdutoServicoId deve ser informado! ")
        UUID produtoServico;

        @NotNull(message = "Quantidade deve ser informada! ")
        @QuantidadePositiva
        Integer quantidade;
    }
}
