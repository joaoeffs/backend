package com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.Tipo;

import lombok.Builder;
import lombok.Value;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface RegistrarProdutoServicoUseCase {

    UUID handle(RegistrarProdutoServico command);

    @Value
    @Builder
    class RegistrarProdutoServico {

        @NotBlank(message = "Descrição não pode ser nula ou vazia! ")
        String descricao;

        @NotNull(message = "Preço não pode ser nulo! ")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
        BigDecimal preco;

        @NotNull(message = "Tipo não pode ser nulo! ")
        Tipo tipo;
    }
}
