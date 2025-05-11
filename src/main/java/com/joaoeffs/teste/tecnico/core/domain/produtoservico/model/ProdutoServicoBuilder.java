package com.joaoeffs.teste.tecnico.core.domain.produtoservico.model;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class ProdutoServicoBuilder {


    private String descricao;

    private Tipo tipo;

    private BigDecimal preco;

    public ProdutoServicoBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoServicoBuilder preco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public ProdutoServicoBuilder tipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public ProdutoServico build() {
        return new ProdutoServico(this);
    }
}
