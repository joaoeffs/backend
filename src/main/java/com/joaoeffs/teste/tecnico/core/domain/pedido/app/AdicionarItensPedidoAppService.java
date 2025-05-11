package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens.ItemPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AdicionarItensPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository.ProdutoServicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AdicionarItensPedidoAppService implements AdicionarItensPedidoUseCase {

    private final PedidoRepository repository;
    private final ProdutoServicoRepository produtoServicoRepository;

    @Override
    public void handle(AdicionarItensPedido command) {

        Pedido pedido = repository.getById(command.getId());

        command.getItens().forEach(cmd -> {
            ProdutoServico produtoServico = produtoServicoRepository.getById(cmd.getProdutoServico());

            ItemPedido itemPedido = ItemPedido.builder()
                .quantidade(cmd.getQuantidade())
                .valorUnitario(produtoServico.getPreco())
                .produtoServico(produtoServico)
                .build();

            pedido.adicionarItem(itemPedido);
            repository.save(pedido);
        });
    }
}
