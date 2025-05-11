package com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase;

import java.util.UUID;

import lombok.Value;

public interface InativarProdutoServicoUseCase {

    void handle(InativarProdutoServico command);

    @Value(staticConstructor = "from")
    class InativarProdutoServico {

        UUID id;
    }
}
