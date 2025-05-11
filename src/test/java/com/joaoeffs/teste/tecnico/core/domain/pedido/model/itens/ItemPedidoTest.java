package com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.Tipo;

class ItemPedidoTest {

    private ItemPedido itemPedido;

    private ProdutoServico produtoServico;

    @BeforeEach
    void setUp() {
        produtoServico = ProdutoServico.builder()
            .descricao("Produto Teste")
            .preco(BigDecimal.valueOf(100.0))
            .tipo(Tipo.PRODUTO)
            .build();

        itemPedido = ItemPedido.builder()
            .quantidade(2)
            .valorUnitario(BigDecimal.valueOf(50.0))
            .produtoServico(produtoServico)
            .build();
    }

    @Nested
    class Builder {

        @Test
        void build() {
            assertNotNull(itemPedido.getId());
            assertNotNull(itemPedido.getQuantidade());
            assertNotNull(itemPedido.getValorUnitario());
            assertNotNull(itemPedido.getProdutoServico());

            assertEquals(2, itemPedido.getQuantidade());
            assertEquals(BigDecimal.valueOf(50.0), itemPedido.getValorUnitario());
            assertEquals(produtoServico, itemPedido.getProdutoServico());
        }
    }

    @Test
    void calcularValorTotal() {
        assertEquals(BigDecimal.valueOf(100.0), itemPedido.calcularValorTotal());
    }

    @Test
    void calcularValorTotalComDesconto() {
        BigDecimal desconto = BigDecimal.valueOf(0.9);
        assertEquals(BigDecimal.valueOf(90.00).setScale(2), itemPedido.calcularValorTotalComDesconto(desconto));
    }

    @Test
    void marcarItemComoDeletado() {
        itemPedido.remover();
        assertFalse(itemPedido.nonDeleted());
    }
}