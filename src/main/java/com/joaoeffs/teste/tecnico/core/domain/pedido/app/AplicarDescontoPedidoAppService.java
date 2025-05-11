package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AplicarDescontoPedidoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AplicarDescontoPedidoAppService implements AplicarDescontoPedidoUseCase {

    private final PedidoRepository repository;

    @Override
    public void handle(AplicarDescontoPedido command) {
        Pedido pedido = repository.getById(command.getId());
        pedido.aplicarDesconto(command.getDesconto());
        repository.save(pedido);
    }
}
