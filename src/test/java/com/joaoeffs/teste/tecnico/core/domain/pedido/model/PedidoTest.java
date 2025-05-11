package com.joaoeffs.teste.tecnico.core.domain.pedido.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens.ItemPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao.Situacao;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.Tipo;
import com.joaoeffs.teste.tecnico.infra.exception.ItemPedidoIdNotFoundException;
import com.joaoeffs.teste.tecnico.infra.exception.NumeroPedidoException;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoInativoException;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoFechadoException;

class PedidoTest {

    private Pedido pedido;

    private ItemPedido itemPedido;

    private ProdutoServico produtoServico;

    @BeforeEach
    void setUp() {
        pedido = Pedido.builder()
            .cliente("Cliente Teste")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        produtoServico = ProdutoServico.builder()
            .descricao("Limpeza")
            .preco(BigDecimal.TEN)
            .tipo(Tipo.SERVICO)
            .build();

        itemPedido = ItemPedido.builder()
            .quantidade(1)
            .valorUnitario(BigDecimal.TEN)
            .produtoServico(produtoServico)
            .build();
    }

    @Nested
    class Builder {

        PedidoBuilder builder = new PedidoBuilder();

        @Test
        void build() {
            Pedido pedido = builder
                .cliente("Cliente Teste")
                .numeroPedido(1)
                .numeroPedidoConstraint(n -> false)
                .build();

            assertNotNull(pedido.getId());
            assertNotNull(pedido.getCliente());
            assertNotNull(pedido.getNumeroPedido());

            assertEquals("Cliente Teste", pedido.getCliente());
            assertEquals(1, pedido.getNumeroPedido());
            assertEquals(Situacao.PENDENTE, pedido.getSituacao());
        }

        @Test
        void buildComNumeroPedidoJaExistente() {
            builder.numeroPedidoConstraint(n -> true);

            assertThrows(NumeroPedidoException.class, builder::build);
        }
    }

    @Nested
    class AdicionarItem {

        @Test
        @DisplayName("Deve adicionar item ao pedido com sucesso")
        void deveAdicionarItemAoPedido() {
            pedido.adicionarItem(itemPedido);

            assertEquals(1, pedido.getItens().size());
            assertEquals(BigDecimal.TEN, pedido.getValorTotal());
        }

        @Test
        @DisplayName("Deve lançar exception ao adicionar item inativo")
        void deveLancarExceptionAoAdicionarItemInativo() {
            produtoServico.inativar();
            assertThrows(ProdutoServicoInativoException.class, () -> pedido.adicionarItem(itemPedido));
        }

        @Test
        @DisplayName("Deve lançar exception ao adicionar item com pedido fechado")
        void deveLancarExceptionAoAdicionarItemPedidoFechado() {
            pedido.fechar();
            assertThrows(PedidoFechadoException.class, () -> pedido.adicionarItem(itemPedido));
        }
    }

    @Nested
    class RemoverItem {

        @Test
        @DisplayName("Deve remover item do pedido com sucesso")
        void deveRemoverItemDoPedido() {
            pedido.adicionarItem(itemPedido);
            pedido.removerItem(itemPedido.getId());

            assertEquals(BigDecimal.ZERO, pedido.getValorTotal());
        }

        @Test
        @DisplayName("Deve lançar exception ao remover item com pedido fechado")
        void deveLancarExceptionAoRemoverItemComPedidoFechado() {
            pedido.fechar();
            assertThrows(PedidoFechadoException.class, () -> pedido.removerItem(itemPedido.getId()));
        }

        @Test
        @DisplayName("Deve lançar exceção ao remover item inexistente")
        void deveLancarExcecaoAoRemoverItemInexistente() {
            UUID itemId = UUID.randomUUID();
            assertThrows(ItemPedidoIdNotFoundException.class, () -> pedido.removerItem(itemId));
        }
    }

    @Nested
    class AplicarDesconto {

        @Test
        @DisplayName("Deve aplicar desconto ao pedido com sucesso")
        void deveAplicarDescontoAoPedidoComSucesso() {

            produtoServico = ProdutoServico.builder()
                .descricao("Café")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.PRODUTO)
                .build();

            itemPedido = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(BigDecimal.TEN)
                .produtoServico(produtoServico)
                .build();

            pedido.adicionarItem(itemPedido);
            pedido.aplicarDesconto(BigDecimal.TEN);

            assertEquals(BigDecimal.TEN, pedido.getValorTotal());
            assertEquals(BigDecimal.TEN, pedido.getPercentualDesconto());
            assertEquals(BigDecimal.valueOf(1.0), pedido.getValorDesconto());
        }

        @Test
        @DisplayName("Não deve aplicar desconto ao pedido com item de serviço")
        void naoDeveAplicarDescontoAoPedidoComItemServico() {

            produtoServico = ProdutoServico.builder()
                .descricao("Serviço de Limpeza")
                .preco(BigDecimal.valueOf(100))
                .tipo(Tipo.SERVICO)
                .build();

            itemPedido = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(produtoServico.getPreco())
                .produtoServico(produtoServico)
                .build();

            pedido.adicionarItem(itemPedido);
            pedido.aplicarDesconto(BigDecimal.TEN);

            assertEquals(BigDecimal.valueOf(100), pedido.getValorTotal());
            assertEquals(BigDecimal.TEN, pedido.getPercentualDesconto());
            assertEquals(BigDecimal.valueOf(0), pedido.getValorDesconto());
        }

        @Test
        @DisplayName("Deve lançar exception ao aplicar desconto em pedido fechado")
        void deveLancarExcecaoAoAplicarDescontoEmPedidoFechado() {
            pedido.fechar();
            assertThrows(PedidoFechadoException.class, () -> pedido.aplicarDesconto(BigDecimal.TEN));
        }
    }

    @Nested
    class FecharPedido {

        @Test
        @DisplayName("Deve fechar pedido com sucesso")
        void deveFecharPedidoComSucesso() {
            pedido.fechar();
            assertEquals(Situacao.FECHADO, pedido.getSituacao());
        }
    }

    @Nested
    class CancelarPedido {

        @Test
        @DisplayName("Deve cancelar pedido com sucesso")
        void deveCancelarPedidoComSucesso() {
            pedido.cancelar();
            assertEquals(Situacao.CANCELADO, pedido.getSituacao());
        }
    }

    @Nested
    class CenariosBDD {

        @Test
        @DisplayName("""
            Cenário 1: Registrar pedido com itens e calculcar valor total
            
            Dado que o usuário deseja registrar um novo pedido
            E adiciona os seguintes itens:
                Produto A: R$ 10,00 (quantidade: 3)
                Produto B: R$ 5,00 (quantidade: 2)
                Serviço C: R$ 100,00 (quantidade: 1)
            Quando o pedido for registrado
            Então o sistema deve calcular o valor total como:
                (3 × 10,00) + (2 × 5,00) + (1 × 100,00) = R$ 30,00 + R$ 10,00 + R$ 100,00
                Totalizando = R$ 140,00
            E retornar o código de status HTTP 201 (Created)
            """)
        void registrarPedidoComItens() {

            var produto1 = ProdutoServico.builder()
                .descricao("Produto A")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.PRODUTO)
                .build();

            var produto2 = ProdutoServico.builder()
                .descricao("Produto B")
                .preco(BigDecimal.valueOf(5))
                .tipo(Tipo.PRODUTO)
                .build();

            var servico = ProdutoServico.builder()
                .descricao("Serviço C")
                .preco(BigDecimal.valueOf(100))
                .tipo(Tipo.SERVICO)
                .build();

            var pedido = Pedido.builder()
                .cliente("Cliente")
                .numeroPedido(1)
                .numeroPedidoConstraint(n -> false)
                .build();

            var item1 = ItemPedido.builder()
                .quantidade(3)
                .valorUnitario(produto1.getPreco())
                .produtoServico(produto1)
                .build();

            var item2 = ItemPedido.builder()
                .quantidade(2)
                .valorUnitario(produto2.getPreco())
                .produtoServico(produto2)
                .build();

            var item3 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(servico.getPreco())
                .produtoServico(servico)
                .build();

            pedido.adicionarItem(item1);
            pedido.adicionarItem(item2);
            pedido.adicionarItem(item3);

            assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(140));
        }

        @Test
        @DisplayName("""
            Cenário 2: Aplicar desconto sobre valor total no pedido
            
            Dado que o pedido está com status ABERTO
            E contém os seguintes itens:
                Produto A: R$ 10,00 (quantidade: 2) → R$ 20,00
                Produto B: R$ 5,00 (quantidade: 4) → R$ 20,00
                Serviço C: R$ 20,00 (quantidade: 1) → R$ 20,00
            Quando o usuário aplicar 10% de desconto
            Então o sistema deve aplicar o desconto somente sobre produtos
                (R$ 40,00 × 10% = R$ 4,00)
            E o valor do desconto do pedido deve ser calculado para: R$ 4,00
            E retornar o código de status HTTP 200 (OK)
            """)
        void aplicarDescontoPedido() {

            var produto1 = ProdutoServico.builder()
                .descricao("Produto A")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.PRODUTO)
                .build();

            var produto2 = ProdutoServico.builder()
                .descricao("Produto B")
                .preco(BigDecimal.valueOf(5))
                .tipo(Tipo.PRODUTO)
                .build();

            var servico = ProdutoServico.builder()
                .descricao("Serviço C")
                .preco(BigDecimal.valueOf(20))
                .tipo(Tipo.SERVICO)
                .build();

            var pedido = Pedido.builder()
                .cliente("Cliente")
                .numeroPedido(1)
                .numeroPedidoConstraint(n -> false)
                .build();

            var item1 = ItemPedido.builder()
                .quantidade(2)
                .valorUnitario(produto1.getPreco())
                .produtoServico(produto1)
                .build();

            var item2 = ItemPedido.builder()
                .quantidade(4)
                .valorUnitario(produto2.getPreco())
                .produtoServico(produto2)
                .build();

            var item3 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(servico.getPreco())
                .produtoServico(servico)
                .build();

            pedido.adicionarItem(item1);
            pedido.adicionarItem(item2);
            pedido.adicionarItem(item3);

            pedido.aplicarDesconto(BigDecimal.TEN);

            assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(60));
            assertEquals(pedido.getPercentualDesconto(), BigDecimal.TEN);
            assertEquals(pedido.getValorDesconto(), BigDecimal.valueOf(4.0));
        }

        @Test
        @DisplayName("""
            Cenário 3: Recalcular valor total ao remover item
            
            Dado que o pedido está com status ABERTO
            E contém os seguintes itens:
                Produto A: R$ 10,00 (quantidade: 5) → R$ 50,00
                Produto B: R$ 5,00 (quantidade: 4) → R$ 20,00
                Serviço C: R$ 30,00 (quantidade: 1) → R$ 30,00
            Quando o usuário remove o item Produto B
            Então o sistema deve recalcular o valor total como:
            R$ 50,00 (Produto A) + R$ 30,00 (Serviço C) = R$ 80,00
            E retornar o código de status HTTP 200 (OK)
            """)
        void recalcularValorTotalAoRemoverItem() {

            var produto1 = ProdutoServico.builder()
                .descricao("Produto A")
                .preco(BigDecimal.TEN)
                .tipo(Tipo.PRODUTO)
                .build();

            var produto2 = ProdutoServico.builder()
                .descricao("Produto B")
                .preco(BigDecimal.valueOf(5))
                .tipo(Tipo.PRODUTO)
                .build();

            var servico = ProdutoServico.builder()
                .descricao("Serviço C")
                .preco(BigDecimal.valueOf(30))
                .tipo(Tipo.SERVICO)
                .build();

            var pedido = Pedido.builder()
                .cliente("Cliente")
                .numeroPedido(1)
                .numeroPedidoConstraint(n -> false)
                .build();

            var item1 = ItemPedido.builder()
                .quantidade(5)
                .valorUnitario(produto1.getPreco())
                .produtoServico(produto1)
                .build();

            var item2 = ItemPedido.builder()
                .quantidade(4)
                .valorUnitario(produto2.getPreco())
                .produtoServico(produto2)
                .build();

            var item3 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(servico.getPreco())
                .produtoServico(servico)
                .build();

            pedido.adicionarItem(item1);
            pedido.adicionarItem(item2);
            pedido.adicionarItem(item3);

            pedido.removerItem(item2.getId());

            assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(80));
        }

        @Test
        @DisplayName("""
            Dado que o pedido está com status ABERTO
            E contém os seguintes itens:
                Produto A: R$ 100,00
                Produto B: R$ 50,00
                Serviço C: R$ 30,00
            E que o usuário já tenha aplicado o desconto de 10%
            E que o valor total seja R$ 180,00
            E que o valor do desconto seja R$ 15,00
            Quanto o usuário adicionar um novo item
                Produto D: R$ 20,00
            Então o sistema deve recalculcar o valor total para R$ 200,00
            E o valor do desconto deve ser recalculado para R$ 17,00
            """)
        void recalcularDescontoAoAdicionarUmNovoItem() {

            var produto1 = ProdutoServico.builder()
                .descricao("Produto A")
                .preco(BigDecimal.valueOf(100))
                .tipo(Tipo.PRODUTO)
                .build();

            var produto2 = ProdutoServico.builder()
                .descricao("Produto B")
                .preco(BigDecimal.valueOf(50))
                .tipo(Tipo.PRODUTO)
                .build();

            var servico = ProdutoServico.builder()
                .descricao("Serviço C")
                .preco(BigDecimal.valueOf(30))
                .tipo(Tipo.SERVICO)
                .build();

            var pedido = Pedido.builder()
                .cliente("Cliente")
                .numeroPedido(1)
                .numeroPedidoConstraint(n -> false)
                .build();

            var item1 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(produto1.getPreco())
                .produtoServico(produto1)
                .build();

            var item2 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(produto2.getPreco())
                .produtoServico(produto2)
                .build();

            var item3 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(servico.getPreco())
                .produtoServico(servico)
                .build();

            pedido.adicionarItem(item1);
            pedido.adicionarItem(item2);
            pedido.adicionarItem(item3);
            pedido.aplicarDesconto(BigDecimal.TEN);

            assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(180));
            assertEquals(pedido.getPercentualDesconto(), BigDecimal.TEN);
            assertEquals(pedido.getValorDesconto(), BigDecimal.valueOf(15.0));

            var produto4 = ProdutoServico.builder()
                .descricao("Produto D")
                .preco(BigDecimal.valueOf(20))
                .tipo(Tipo.PRODUTO)
                .build();

            var item4 = ItemPedido.builder()
                .quantidade(1)
                .valorUnitario(produto4.getPreco())
                .produtoServico(produto4)
                .build();

            pedido.adicionarItem(item4);

            assertEquals(pedido.getValorTotal(), BigDecimal.valueOf(200));
            assertEquals(pedido.getPercentualDesconto(), BigDecimal.TEN);
            assertEquals(pedido.getValorDesconto(), BigDecimal.valueOf(17.0));
        }
    }

}