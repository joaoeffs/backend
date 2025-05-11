package com.joaoeffs.teste.tecnico.query.pedido;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = "/scripts/data.sql")
class PedidoQueryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listagem() throws Exception {
        mockMvc.perform(get("/api/pedido"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(3)))

            .andExpect(jsonPath("$.items[0].id", is("48337951-18f1-4cc3-9178-3c3bcd522e6b")))
            .andExpect(jsonPath("$.items[0].cliente", is("Edu")))
            .andExpect(jsonPath("$.items[0].numeroPedido", is(3)))
            .andExpect(jsonPath("$.items[0].situacao", is("FECHADO")))
            .andExpect(jsonPath("$.items[0].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[0].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[0].valorDesconto", is(0.0)))

            .andExpect(jsonPath("$.items[1].id", is("446961a6-8980-41df-88e1-2c4d13f28293")))
            .andExpect(jsonPath("$.items[1].cliente", is("João")))
            .andExpect(jsonPath("$.items[1].numeroPedido", is(2)))
            .andExpect(jsonPath("$.items[1].situacao", is("PENDENTE")))
            .andExpect(jsonPath("$.items[1].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[1].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[1].valorDesconto", is(0.0)))

            .andExpect(jsonPath("$.items[2].id", is("8c5390a2-315e-44be-80d8-d31aa2cc8f77")))
            .andExpect(jsonPath("$.items[2].cliente", is("Joana")))
            .andExpect(jsonPath("$.items[2].numeroPedido", is(1)))
            .andExpect(jsonPath("$.items[2].situacao", is("CANCELADO")))
            .andExpect(jsonPath("$.items[2].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[2].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[2].valorDesconto", is(0.0)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/pedido/{id}", "48337951-18f1-4cc3-9178-3c3bcd522e6b"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is("48337951-18f1-4cc3-9178-3c3bcd522e6b")))
            .andExpect(jsonPath("$.cliente", is("Edu")))
            .andExpect(jsonPath("$.numeroPedido", is(3)))
            .andExpect(jsonPath("$.situacao", is("FECHADO")))
            .andExpect(jsonPath("$.valorTotal", is(0.0)))
            .andExpect(jsonPath("$.percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.valorDesconto", is(0.0)));
    }

    @Test
    void filtroCliente() throws Exception {
        mockMvc.perform(get("/api/pedido")
                .param("cliente", "João"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(1)))

            .andExpect(jsonPath("$.items[0].id", is("446961a6-8980-41df-88e1-2c4d13f28293")))
            .andExpect(jsonPath("$.items[0].cliente", is("João")))
            .andExpect(jsonPath("$.items[0].numeroPedido", is(2)))
            .andExpect(jsonPath("$.items[0].situacao", is("PENDENTE")))
            .andExpect(jsonPath("$.items[0].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[0].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[0].valorDesconto", is(0.0)));
    }

    @Test
    void filtroSituacao() throws Exception {
        mockMvc.perform(get("/api/pedido")
                .param("situacoes", "PENDENTE"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(1)))

            .andExpect(jsonPath("$.items[0].id", is("446961a6-8980-41df-88e1-2c4d13f28293")))
            .andExpect(jsonPath("$.items[0].cliente", is("João")))
            .andExpect(jsonPath("$.items[0].numeroPedido", is(2)))
            .andExpect(jsonPath("$.items[0].situacao", is("PENDENTE")))
            .andExpect(jsonPath("$.items[0].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[0].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[0].valorDesconto", is(0.0)));
    }

    @Test
    void filtroNumeroPedido() throws Exception {
        mockMvc.perform(get("/api/pedido")
                .param("numeroPedido", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(1)))

            .andExpect(jsonPath("$.items[0].id", is("8c5390a2-315e-44be-80d8-d31aa2cc8f77")))
            .andExpect(jsonPath("$.items[0].cliente", is("Joana")))
            .andExpect(jsonPath("$.items[0].numeroPedido", is(1)))
            .andExpect(jsonPath("$.items[0].situacao", is("CANCELADO")))
            .andExpect(jsonPath("$.items[0].valorTotal", is(0.0)))
            .andExpect(jsonPath("$.items[0].percentualDesconto", is(0.0)))
            .andExpect(jsonPath("$.items[0].valorDesconto", is(0.0)));
    }

    @Test
    void listarItensPedido() throws Exception {
        mockMvc.perform(get("/api/pedido/{id}/itens", "446961a6-8980-41df-88e1-2c4d13f28293"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(3)))

            .andExpect(jsonPath("$.items[0].id", is("5fb59977-d85c-4c24-940a-c6ea925ba798")))
            .andExpect(jsonPath("$.items[0].quantidade", is(1)))
            .andExpect(jsonPath("$.items[0].valorUnitario", is(50.00)))
            .andExpect(jsonPath("$.items[0].produtoServico.id", is("f1ba310b-1f35-4128-974c-bb8146b85f3d")))
            .andExpect(jsonPath("$.items[0].produtoServico.descricao", is("Produto A")))
            .andExpect(jsonPath("$.items[0].produtoServico.tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[0].produtoServico.preco", is(10.00)))
            .andExpect(jsonPath("$.items[0].produtoServico.situacao", is("ATIVO")))

            .andExpect(jsonPath("$.items[1].id", is("1f5d9e0b-df59-4a0e-8f91-e1a274eb3731")))
            .andExpect(jsonPath("$.items[1].quantidade", is(1)))
            .andExpect(jsonPath("$.items[1].valorUnitario", is(100.0)))
            .andExpect(jsonPath("$.items[1].produtoServico.id", is("54818486-b87b-4adb-99a7-55ceb558fab9")))
            .andExpect(jsonPath("$.items[1].produtoServico.descricao", is("SERVICO A")))
            .andExpect(jsonPath("$.items[1].produtoServico.tipo", is("SERVICO")))
            .andExpect(jsonPath("$.items[1].produtoServico.preco", is(100.00)))
            .andExpect(jsonPath("$.items[1].produtoServico.situacao", is("ATIVO")))

            .andExpect(jsonPath("$.items[2].id", is("daf4b972-3a93-456a-89d4-7111219792d2")))
            .andExpect(jsonPath("$.items[2].quantidade", is(2)))
            .andExpect(jsonPath("$.items[2].valorUnitario", is(10.0)))
            .andExpect(jsonPath("$.items[2].produtoServico.id", is("21badd45-e2d3-405b-8ba8-176b4a9dc1bb")))
            .andExpect(jsonPath("$.items[2].produtoServico.descricao", is("SERVICO B")))
            .andExpect(jsonPath("$.items[2].produtoServico.tipo", is("SERVICO")))
            .andExpect(jsonPath("$.items[2].produtoServico.preco", is(250.00)))
            .andExpect(jsonPath("$.items[2].produtoServico.situacao", is("ATIVO")));
    }
}
