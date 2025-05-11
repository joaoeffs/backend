package com.joaoeffs.teste.tecnico.core.domain.pedido.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens.ItemPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AdicionarItensPedidoUseCase.AdicionarItensPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AdicionarItensPedidoUseCase.Item;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RemoverItensPedidoUseCase.Itens;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RemoverItensPedidoUseCase.RemoverItensPedido;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.Tipo;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PedidoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Test
    void registrarPedido() throws Exception {

        var command = """
                {
                    "cliente": "Joao",
                    "numeroPedido": 1231
                }
                """;

        mockMvc.perform(post("/api/v1/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(command))
            .andExpect(status().isCreated());
    }

    @Test
    void adicionarItens() throws Exception {

        Pedido pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        pedidoRepository.save(pedido);

        ProdutoServico produtoServico = ProdutoServico.builder()
            .tipo(Tipo.PRODUTO)
            .preco(BigDecimal.TEN)
            .descricao("Produto")
            .build();

        produtoServicoRepository.save(produtoServico);

        var item = Item.builder()
            .produtoServico(produtoServico.getId())
            .quantidade(2)
            .build();

        var command = AdicionarItensPedido.builder()
                .itens(Set.of(item))
                .build();

        String json = objectMapper.writeValueAsString(command);

        mockMvc.perform(put("/api/v1/pedido/{id}/adicionar-itens", pedido.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void removerItens() throws Exception {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .tipo(Tipo.PRODUTO)
            .preco(BigDecimal.TEN)
            .descricao("Produto")
            .build();

        produtoServicoRepository.save(produtoServico);

        Pedido pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        var item = ItemPedido.builder()
            .produtoServico(produtoServico)
            .quantidade(2)
            .valorUnitario(BigDecimal.TEN)
            .build();

        pedido.adicionarItem(item);
        pedidoRepository.save(pedido);

        var itens = Itens.builder()
            .itemPedido(item.getId())
            .build();

        var command = RemoverItensPedido.builder()
            .itens(Set.of(itens))
            .build();

        String json = objectMapper.writeValueAsString(command);

        mockMvc.perform(put("/api/v1/pedido/{id}/remover-itens", pedido.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void aplicarDesconto() throws Exception {

        Pedido pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        pedidoRepository.save(pedido);

        var command = """
                {
                    "desconto": 10
                }
                """;

        mockMvc.perform(put("/api/v1/pedido/{id}/aplicar-desconto", pedido.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(command))
            .andExpect(status().isOk());
    }

    @Test
    void fecharPedido() throws Exception {

        Pedido pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        pedidoRepository.save(pedido);

        mockMvc.perform(put("/api/v1/pedido/{id}/fechar", pedido.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void cancelarPedido() throws Exception {

        Pedido pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();

        pedidoRepository.save(pedido);

        mockMvc.perform(put("/api/v1/pedido/{id}/cancelar", pedido.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
