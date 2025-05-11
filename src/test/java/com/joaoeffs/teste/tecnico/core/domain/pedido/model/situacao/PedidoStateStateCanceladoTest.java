package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoCanceladoException;

class PedidoStateStateCanceladoTest {

    private static Pedido pedido;

    private final PedidoStateStateCancelado state = new PedidoStateStateCancelado();

    @BeforeAll
    static void beforeAll() {
        pedido = Pedido.builder()
            .cliente("Joao")
            .numeroPedido(1)
            .numeroPedidoConstraint(n -> false)
            .build();
    }

    @Test
    void fechar() {
        assertThrows(PedidoCanceladoException.class, () -> state.fechar(pedido));
    }

    @Test
    void cancelar() {
        assertThrows(PedidoCanceladoException.class, () -> state.cancelar(pedido));
    }
}
