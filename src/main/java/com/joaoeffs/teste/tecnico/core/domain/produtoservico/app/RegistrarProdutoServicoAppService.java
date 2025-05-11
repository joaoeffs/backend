package com.joaoeffs.teste.tecnico.core.domain.produtoservico.app;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RegistrarProdutoServicoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RegistrarProdutoServicoAppService implements RegistrarProdutoServicoUseCase {

    private final ProdutoServicoRepository repository;

    @Override
    public UUID handle(RegistrarProdutoServico command) {

        ProdutoServico produtoServico = ProdutoServico.builder()
            .descricao(command.getDescricao())
            .preco(command.getPreco())
            .tipo(command.getTipo())
            .build();

        repository.save(produtoServico);
        return produtoServico.getId();
    }
}
