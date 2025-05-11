package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RemoverItensPedidoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RemoverItensPedidoAppService implements RemoverItensPedidoUseCase {

    private final PedidoRepository repository;

    @Override
    public void handle(RemoverItensPedido command) {
        Pedido pedido = repository.getById(command.getId());

        command.getItens().forEach(cmd -> {
            pedido.removerItem(cmd.getItemPedido());
            repository.save(pedido);
        });

    }
}
