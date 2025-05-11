package com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens;

import java.math.BigDecimal;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;

import lombok.Getter;

@Getter
public class ItemPedidoBuilder {

    private Integer quantidade;

    private BigDecimal valorUnitario;

    private ProdutoServico produtoServico;

    public ItemPedidoBuilder quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ItemPedidoBuilder valorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        return this;
    }

    public ItemPedidoBuilder produtoServico(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
        return this;
    }

    public ItemPedido build() {
        return new ItemPedido(this);
    }
}
