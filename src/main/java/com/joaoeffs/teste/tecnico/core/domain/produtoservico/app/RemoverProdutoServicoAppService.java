package com.joaoeffs.teste.tecnico.core.domain.produtoservico.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RemoverProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoVinculadoPedidoException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RemoverProdutoServicoAppService implements RemoverProdutoServicoUseCase {

    private final ProdutoServicoRepository repository;

    private final PedidoRepository pedidoRepository;

    @Override
    public void handle(RemoverProdutoServico command) {
        if (pedidoRepository.existsByProdutoServicoId(command.getId()))
            throw new ProdutoServicoVinculadoPedidoException("Não é possível remover o produto/serviço, pois ele está vinculado a um pedido.");

        repository.deleteById(command.getId());
    }
}
