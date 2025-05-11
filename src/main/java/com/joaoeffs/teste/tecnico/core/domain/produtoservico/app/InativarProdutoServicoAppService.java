package com.joaoeffs.teste.tecnico.core.domain.produtoservico.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.InativarProdutoServicoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class InativarProdutoServicoAppService implements InativarProdutoServicoUseCase {

    private final ProdutoServicoRepository repository;

    @Override
    public void handle(InativarProdutoServico command) {
        ProdutoServico produtoServico = repository.getById(command.getId());
        produtoServico.inativar();
        repository.save(produtoServico);
    }
}
