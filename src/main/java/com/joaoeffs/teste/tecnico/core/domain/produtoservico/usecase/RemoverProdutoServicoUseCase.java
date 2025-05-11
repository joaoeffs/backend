package com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase;

import java.util.UUID;

import lombok.Value;

public interface RemoverProdutoServicoUseCase {

    void handle(RemoverProdutoServico command);

    @Value(staticConstructor = "from")
    class RemoverProdutoServico {

        UUID id;
    }
}
