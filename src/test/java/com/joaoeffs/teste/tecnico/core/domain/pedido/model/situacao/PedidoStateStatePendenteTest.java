package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;

class PedidoStateStatePendenteTest {

    private static Pedido pedido;

    private final PedidoStateStatePendente state = new PedidoStateStatePendente();

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
        var situacao = state.fechar(pedido);

        assertEquals(Situacao.FECHADO, situacao);
    }

    @Test
    void cancelar() {
        var situacao = state.cancelar(pedido);

        assertEquals(Situacao.CANCELADO, situacao);
    }
}
