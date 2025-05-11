package com.joaoeffs.teste.tecnico.core.api.produtoservico;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AlterarProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AlterarProdutoServicoUseCase.AlterarProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AtivarProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.AtivarProdutoServicoUseCase.AtivarProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.InativarProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.InativarProdutoServicoUseCase.InativarProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RegistrarProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RegistrarProdutoServicoUseCase.RegistrarProdutoServico;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RemoverProdutoServicoUseCase;
import com.joaoeffs.teste.tecnico.core.domain.produtoservico.usecase.RemoverProdutoServicoUseCase.RemoverProdutoServico;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/produto-servico", produces = APPLICATION_JSON_VALUE)
public class ProdutoServicoController {

    private final RegistrarProdutoServicoUseCase registrarProdutoServicoUseCase;
    private final AlterarProdutoServicoUseCase alterarProdutoServicoUseCase;
    private final InativarProdutoServicoUseCase inativarProdutoServicoUseCase;
    private final AtivarProdutoServicoUseCase ativoProdutoServicoUseCase;
    private final RemoverProdutoServicoUseCase removerProdutoServicoUseCase;

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegistrarProdutoServico command) {
        var id = registrarProdutoServicoUseCase.handle(command);

        return ResponseEntity.created(fromCurrentRequest()
            .path("/{id}")
            .build(id.toString()))
            .build();
    }

    @PutMapping(path = "/{id}")
    public void alterar(@PathVariable UUID id, @Valid @RequestBody AlterarProdutoServico command) {
        alterarProdutoServicoUseCase.handle(command.withId(id));
    }

    @PutMapping(path = "/{id}/inativar")
    public void inativar(@PathVariable UUID id) {
        inativarProdutoServicoUseCase.handle(InativarProdutoServico.from(id));
    }

    @PutMapping(path = "/{id}/ativar")
    public void ativar(@PathVariable UUID id) {
        ativoProdutoServicoUseCase.handle(AtivarProdutoServico.from(id));
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable UUID id) {
        removerProdutoServicoUseCase.handle(RemoverProdutoServico.from(id));
    }
}
