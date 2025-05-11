package com.joaoeffs.teste.tecnico.query.api.produtoservico;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoeffs.teste.tecnico.query.domain.produtoservico.appservice.ProdutoServicoAppService;
import com.joaoeffs.teste.tecnico.query.domain.produtoservico.criteria.ProdutoServicoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.produtoservico.model.ProdutoServicoQuery;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/produto-servico", produces = APPLICATION_JSON_VALUE)
public class ProdutoServicoQueryController {

    private final ProdutoServicoAppService appService;

    @GetMapping
    public Slice<ProdutoServicoQuery> listar(@ParameterObject ProdutoServicoCriteria criteria, @ParameterObject Pageable pageable) {
        return appService.filtrar(criteria, pageable);
    }

    @GetMapping(path = "/{id}")
    public ProdutoServicoQuery getById(@PathVariable UUID id) {
        return appService.getById(id);
    }
}
