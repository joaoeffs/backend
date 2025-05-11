package com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoFechadoException;

class PedidoStateStateFechadoTest {

    private static Pedido pedido;

    private final PedidoStateStateFechado state = new PedidoStateStateFechado();

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
        assertThrows(PedidoFechadoException.class, () -> state.fechar(pedido));
    }

    @Test
    void cancelar() {
        assertThrows(PedidoFechadoException.class, () -> state.cancelar(pedido));
    }
}
