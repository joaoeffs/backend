package com.joaoeffs.teste.tecnico.query.domain.pedido.appservice;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.query.domain.pedido.criteria.PedidoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.PedidoQuery;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.item.ItemPedidoQuery;
import com.joaoeffs.teste.tecnico.query.domain.pedido.repository.PedidoQueryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PedidoQueryAppService {

    private final PedidoQueryRepository repository;

    public Slice<PedidoQuery> filtrar(PedidoCriteria criteria, Pageable pageable) {
        return repository.filtrar(criteria, pageable);
    }

    public PedidoQuery getById(UUID id) {
        return repository.findProjectedById(id);
    }

    public Slice<ItemPedidoQuery> listarItens(UUID id, Pageable pageable) {
        return repository.findItensByPedidoId(id, pageable);
    }
}
