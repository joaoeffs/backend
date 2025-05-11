package com.joaoeffs.teste.tecnico.core.domain.produtoservico.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AlterarProdutoServicoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AlterarProdutoServicoAppService implements AlterarProdutoServicoUseCase {

    private final ProdutoServicoRepository repository;

    @Override
    public void handle(AlterarProdutoServico command) {
        ProdutoServico produtoServico = repository.getById(command.getId());

        produtoServico.alterar()
            .descricao(command.getDescricao())
            .preco(command.getPreco())
            .apply();

        repository.save(produtoServico);
    }
}
