package com.joaoeffs.teste.tecnico.core.api.pedido;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AdicionarItensPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AdicionarItensPedidoUseCase.AdicionarItensPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AplicarDescontoPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.AplicarDescontoPedidoUseCase.AplicarDescontoPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.CancelarPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.CancelarPedidoUseCase.CancelarPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.FecharPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.FecharPedidoUseCase.FecharPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RegistrarPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RegistrarPedidoUseCase.RegistrarPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RemoverItensPedidoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.pedido.usecase.RemoverItensPedidoUseCase.RemoverItensPedido;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/pedido", produces = APPLICATION_JSON_VALUE)
public class PedidoController {

    private final RegistrarPedidoUseCase registrarPedidoUseCase;
    private final AdicionarItensPedidoUseCase adicionarItensPedidoUseCase;
    private final RemoverItensPedidoUseCase removerItensPedidoUseCase;
    private final AplicarDescontoPedidoUseCase aplicarDescontoPedidoUseCase;
    private final FecharPedidoUseCase fecharPedidoUseCase;
    private final CancelarPedidoUseCase cancelarPedidoUseCase;

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegistrarPedido command) {
        var id = registrarPedidoUseCase.handle(command);

        return ResponseEntity.created(fromCurrentRequest()
            .path("/{id}")
            .build(id.toString()))
            .build();
    }

    @PutMapping(path = "/{id}/adicionar-itens")
    public void adicionarItens(@PathVariable UUID id, @Valid @RequestBody AdicionarItensPedido command) {
        adicionarItensPedidoUseCase.handle(command.withId(id));
    }

    @PutMapping(path = "/{id}/remover-itens")
    public void removerItens(@PathVariable UUID id, @Valid @RequestBody RemoverItensPedido command) {
        removerItensPedidoUseCase.handle(command.withId(id));
    }

    @PutMapping(path = "/{id}/aplicar-desconto")
    public void aplicarDesconto(@PathVariable UUID id, @Valid @RequestBody AplicarDescontoPedido command) {
        aplicarDescontoPedidoUseCase.handle(command.withId(id));
    }

    @PutMapping(path = "/{id}/fechar")
    public void fecharPedido(@PathVariable UUID id) {
        fecharPedidoUseCase.handle(FecharPedido.from(id));
    }

    @PutMapping(path = "/{id}/cancelar")
    public void cancelarPedido(@PathVariable UUID id) {
        cancelarPedidoUseCase.handle(CancelarPedido.from(id));
    }
}
