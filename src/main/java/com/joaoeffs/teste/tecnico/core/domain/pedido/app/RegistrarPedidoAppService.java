package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RegistrarPedidoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RegistrarPedidoAppService implements RegistrarPedidoUseCase {

    private final PedidoRepository repository;

    @Override
    public UUID handle(RegistrarPedido command) {

        Pedido pedido = Pedido.builder()
            .cliente(command.getCliente())
            .numeroPedido(command.getNumeroPedido())
            .numeroPedidoConstraint(repository::existsByNumeroPedido)
            .build();

        repository.save(pedido);
        return pedido.getId();
    }
}
