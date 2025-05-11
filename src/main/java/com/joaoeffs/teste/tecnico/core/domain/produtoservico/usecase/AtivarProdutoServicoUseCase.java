package com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase;

import java.util.UUID;

import lombok.Value;

public interface AtivarProdutoServicoUseCase {

    void handle(AtivarProdutoServico command);

    @Value(staticConstructor = "from")
    class AtivarProdutoServico {

        UUID id;
    }
}
