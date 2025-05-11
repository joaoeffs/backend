package com.joaoeffs.teste.tecnico.query.api.pedido;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoeffs.teste.tecnico.query.domain.pedido.appservice.PedidoQueryAppService;
import com.joaoeffs.teste.tecnico.query.domain.pedido.criteria.PedidoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.PedidoQuery;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.item.ItemPedidoQuery;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/pedido", produces = APPLICATION_JSON_VALUE)
public class PedidoQueryController {

    private final PedidoQueryAppService queryAppService;

    @GetMapping
    public Slice<PedidoQuery> listar(@ParameterObject PedidoCriteria criteria, @ParameterObject Pageable pageable) {
        return queryAppService.filtrar(criteria, pageable);
    }

    @GetMapping(path = "/{id}")
    public PedidoQuery getById(@PathVariable UUID id) {
        return queryAppService.getById(id);
    }

    @GetMapping(path = "/{id}/itens")
    public Slice<ItemPedidoQuery> listarItens(@PathVariable UUID id, @ParameterObject Pageable pageable) {
        return queryAppService.listarItens(id, pageable);
    }
}
