package com.joaoeffs.teste.tecnico.core.domain.pedido.usecase;

import java.util.UUID;

import lombok.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface RegistrarPedidoUseCase {

    UUID handle(RegistrarPedido command);

    @Value
    class RegistrarPedido {

        @NotNull(message = "Cliente deve ser informado! ")
        @NotBlank(message = "Cliente não pode ser vazio! ")
        String cliente;

        @NotNull(message = "Número pedido deve ser informado!")
        Integer numeroPedido;
    }
}
