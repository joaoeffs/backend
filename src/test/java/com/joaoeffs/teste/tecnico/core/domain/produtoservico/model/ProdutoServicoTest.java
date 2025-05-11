package com.joaoeffs.teste.tecnico.core.domain.produtoservico.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProdutoServicoTest {

    private ProdutoServico produtoServico;

    @BeforeEach
    void setUp() {
        produtoServico = ProdutoServico.builder()
            .descricao("Produto Teste")
            .preco(BigDecimal.TEN)
            .tipo(Tipo.PRODUTO)
            .build();
    }

    @Nested
    class Builder {

        ProdutoServicoBuilder builder = new ProdutoServicoBuilder();

        @Test
        void buildProduto() {
            ProdutoServico produto = builder
                .descricao("Produto Teste")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.PRODUTO)
                .build();

            assertNotNull(produto.getId());
            assertNotNull(produto.getDescricao());
            assertNotNull(produto.getPreco());

            assertEquals("Produto Teste", produtoServico.getDescricao());
            assertEquals(BigDecimal.TEN, produtoServico.getPreco());
            assertEquals(Tipo.PRODUTO, produtoServico.getTipo());
        }

        @Test
        void buildServico() {
            ProdutoServico servico = builder
                .descricao("Serviço Teste")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.SERVICO)
                .build();

            assertNotNull(servico.getId());
            assertNotNull(servico.getDescricao());
            assertNotNull(servico.getPreco());

            assertEquals("Serviço Teste", servico.getDescricao());
            assertEquals(BigDecimal.TEN, servico.getPreco());
            assertEquals(Tipo.SERVICO, servico.getTipo());
        }
    }

    @Nested
    class BuilderUpdate {

        ProdutoServicoBuilderUpdate builderUpdate;

        @Test
        void alterar() {

            produtoServico.alterar()
                .descricao("Produto Atualizado")
                .preco(BigDecimal.valueOf(20))
                .apply();

            assertNotNull(produtoServico.getDescricao());
            assertNotNull(produtoServico.getPreco());

            assertEquals("Produto Atualizado", produtoServico.getDescricao());
            assertEquals(BigDecimal.valueOf(20), produtoServico.getPreco());
        }
    }

    @Nested
    class Ativar {

        @Test
        void deveAtivarProdutoServico() {
            produtoServico.ativar();
            assertEquals(Situacao.ATIVO, produtoServico.getSituacao());
        }
    }

    @Nested
    class Inativar {

        @Test
        void deveInativarProdutoServico() {
            produtoServico.inativar();
            assertEquals(Situacao.INATIVO, produtoServico.getSituacao());
        }
    }
}