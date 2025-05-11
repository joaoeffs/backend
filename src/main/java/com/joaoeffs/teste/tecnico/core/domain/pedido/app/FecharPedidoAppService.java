package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.FecharPedidoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class FecharPedidoAppService implements FecharPedidoUseCase {

    private final PedidoRepository repository;

    @Override
    public void handle(FecharPedido command) {

        Pedido pedido = repository.getById(command.getPedido());
        pedido.fechar();
        repository.save(pedido);
    }
}
