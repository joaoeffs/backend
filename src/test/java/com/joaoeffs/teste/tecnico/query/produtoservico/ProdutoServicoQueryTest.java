package com.joaoeffs.teste.tecnico.query.produtoservico;

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
class ProdutoServicoQueryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listagem() throws Exception {
        mockMvc.perform(get("/api/produto-servico"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(4)))

            .andExpect(jsonPath("$.items[0].id", is( "f1ba310b-1f35-4128-974c-bb8146b85f3d")))
            .andExpect(jsonPath("$.items[0].descricao", is("Produto A")))
            .andExpect(jsonPath("$.items[0].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[0].situacao", is("ATIVO")))
            .andExpect(jsonPath("$.items[0].preco", is(10.0)))

            .andExpect(jsonPath("$.items[1].id", is("3409ab1d-d090-4fcc-9bf2-d3415dc09eeb")))
            .andExpect(jsonPath("$.items[1].descricao", is("Produto B")))
            .andExpect(jsonPath("$.items[1].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[1].situacao", is("INATIVO")))
            .andExpect(jsonPath("$.items[1].preco", is(20.0)))

            .andExpect(jsonPath("$.items[2].id", is("54818486-b87b-4adb-99a7-55ceb558fab9")))
            .andExpect(jsonPath("$.items[2].descricao", is("SERVICO A")))
            .andExpect(jsonPath("$.items[2].tipo", is("SERVICO")))
            .andExpect(jsonPath("$.items[2].situacao", is("ATIVO")))
            .andExpect(jsonPath("$.items[2].preco", is(100.0)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/produto-servico/{id}", "f1ba310b-1f35-4128-974c-bb8146b85f3d"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is("f1ba310b-1f35-4128-974c-bb8146b85f3d")))
            .andExpect(jsonPath("$.descricao", is("Produto A")))
            .andExpect(jsonPath("$.tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.situacao", is("ATIVO")))
            .andExpect(jsonPath("$.preco", is(10.0)));
    }

    @Test
    void filtroDescricao() throws Exception {
        mockMvc.perform(get("/api/produto-servico")
                .param("descricao", "pro"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))

            .andExpect(jsonPath("$.items[0].id", is( "f1ba310b-1f35-4128-974c-bb8146b85f3d")))
            .andExpect(jsonPath("$.items[0].descricao", is("Produto A")))
            .andExpect(jsonPath("$.items[0].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[0].situacao", is("ATIVO")))
            .andExpect(jsonPath("$.items[0].preco", is(10.0)))

            .andExpect(jsonPath("$.items[1].id", is("3409ab1d-d090-4fcc-9bf2-d3415dc09eeb")))
            .andExpect(jsonPath("$.items[1].descricao", is("Produto B")))
            .andExpect(jsonPath("$.items[1].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[1].situacao", is("INATIVO")))
            .andExpect(jsonPath("$.items[1].preco", is(20.0)));
    }

    @Test
    void filtroTipo() throws Exception {
        mockMvc.perform(get("/api/produto-servico")
                .param("tipo", "SERVICO"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(2)))

            .andExpect(jsonPath("$.items[0].id", is("54818486-b87b-4adb-99a7-55ceb558fab9")))
            .andExpect(jsonPath("$.items[0].descricao", is("SERVICO A")))
            .andExpect(jsonPath("$.items[0].tipo", is("SERVICO")))
            .andExpect(jsonPath("$.items[0].situacao", is("ATIVO")))
            .andExpect(jsonPath("$.items[0].preco", is(100.0)));
    }

    @Test
    void filtroSituacao() throws Exception {
        mockMvc.perform(get("/api/produto-servico")
                .param("situacao", "INATIVO"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(1)))

            .andExpect(jsonPath("$.items[0].id", is("3409ab1d-d090-4fcc-9bf2-d3415dc09eeb")))
            .andExpect(jsonPath("$.items[0].descricao", is("Produto B")))
            .andExpect(jsonPath("$.items[0].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[0].situacao", is("INATIVO")))
            .andExpect(jsonPath("$.items[0].preco", is(20.0)));
    }

    @Test
    void filtroPreco() throws Exception {
        mockMvc.perform(get("/api/produto-servico")
                .param("preco", "10.0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hasNext", is(false)))
            .andExpect(jsonPath("$.items", hasSize(1)))

            .andExpect(jsonPath("$.items[0].id", is("f1ba310b-1f35-4128-974c-bb8146b85f3d")))
            .andExpect(jsonPath("$.items[0].descricao", is("Produto A")))
            .andExpect(jsonPath("$.items[0].tipo", is("PRODUTO")))
            .andExpect(jsonPath("$.items[0].situacao", is("ATIVO")))
            .andExpect(jsonPath("$.items[0].preco", is(10.0)));
    }

}
