package com.joaoeffs.teste.tecnico.core.domain.produtoservico.model;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;

@Getter
public class ProdutoServicoBuilderUpdate {

    private final Consumer<ProdutoServicoBuilderUpdate> action;

    private UUID id;

    private String descricao;

    private BigDecimal preco;

    ProdutoServicoBuilderUpdate(UUID id, Consumer<ProdutoServicoBuilderUpdate> action) {
        this.id = requireNonNull(id);
        this.action = requireNonNull(action);
    }

    public ProdutoServicoBuilderUpdate descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoServicoBuilderUpdate preco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public void apply() {
        action.accept(this);
    }

}
