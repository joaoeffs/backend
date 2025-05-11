package com.joaoeffs.teste.tecnico.core.domain.produtoservico.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.Tipo;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoServicoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Test
    void registrarProdutoServico() throws Exception {

        var command = """
                {
                    "descricao": "Limpeza",
                    "preco": 100,
                    "tipo": "SERVICO"
                }
                """;

        mockMvc.perform(post("/api/v1/produto-servico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(command))
            .andExpect(status().isCreated());
    }

    @Test
    void alterarProdutoServico() throws Exception {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .descricao("Limpeza")
            .preco(BigDecimal.valueOf(100))
            .tipo(Tipo.SERVICO)
            .build();

        produtoServicoRepository.save(produtoServico);

        var command = """
                {
                    "descricao": "Pintura",
                    "preco": 75
                }
                """;

        mockMvc.perform(put("/api/v1/produto-servico/{id}", produtoServico.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(command))
            .andExpect(status().isOk());
    }

    @Test
    void inativarProdutoServico() throws Exception {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .descricao("Limpeza")
            .preco(BigDecimal.valueOf(100))
            .tipo(Tipo.SERVICO)
            .build();

        produtoServicoRepository.save(produtoServico);

        mockMvc.perform(put("/api/v1/produto-servico/{id}/inativar", produtoServico.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void ativarProdutoServico() throws Exception {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .descricao("Limpeza")
            .preco(BigDecimal.valueOf(100))
            .tipo(Tipo.SERVICO)
            .build();

        produtoServicoRepository.save(produtoServico);

        mockMvc.perform(put("/api/v1/produto-servico/{id}/ativar", produtoServico.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void removerProdutoServico() throws Exception {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .descricao("Limpeza")
            .preco(BigDecimal.valueOf(100))
            .tipo(Tipo.SERVICO)
            .build();

        produtoServicoRepository.save(produtoServico);

        mockMvc.perform(delete("/api/v1/produto-servico/{id}", produtoServico.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
