package com.joaoeffs.teste.tecnico.core.domain.produtoservico.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AtivarProdutoServicoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AtivarProdutoServicoAppService implements AtivarProdutoServicoUseCase {

    private final ProdutoServicoRepository repository;

    @Override
    public void handle(AtivarProdutoServico command) {
        ProdutoServico produtoServico = repository.getById(command.getId());
        produtoServico.ativar();
        repository.save(produtoServico);
    }
}
