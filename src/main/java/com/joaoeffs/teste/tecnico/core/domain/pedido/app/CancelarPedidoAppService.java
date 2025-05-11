package com.joaoeffs.teste.tecnico.core.domain.pedido.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.repository.PedidoRepository;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.CancelarPedidoUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CancelarPedidoAppService implements CancelarPedidoUseCase {

    private final PedidoRepository repository;

    @Override
    public void handle(CancelarPedido command) {

        Pedido pedido = repository.getById(command.getId());
        pedido.cancelar();
        repository.save(pedido);
    }
}
