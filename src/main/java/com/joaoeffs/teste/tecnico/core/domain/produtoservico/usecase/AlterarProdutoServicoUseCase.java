package com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface AlterarProdutoServicoUseCase {

    void handle(AlterarProdutoServico command);

    @Value
    @Builder
    class AlterarProdutoServico {

        @With
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        UUID id;

        @NotBlank(message = "Descrição não pode ser nula ou vazia! ")
        String descricao;

        @NotNull(message = "Preço não pode ser nulo! ")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero! ")
        BigDecimal preco;
    }
}
