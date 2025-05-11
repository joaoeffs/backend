package com.joaoeffs.teste.tecnico.query.domain.produtoservico.appservice;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoeffs.teste.tecnico.query.domain.produtoservico.criteria.ProdutoServicoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.produtoservico.model.ProdutoServicoQuery;
import com.joaoeffs.teste.tecnico.query.domain.produtoservico.repository.ProdutoServicoQueryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProdutoServicoAppService {

    private final ProdutoServicoQueryRepository repository;

    public Slice<ProdutoServicoQuery> filtrar(ProdutoServicoCriteria criteria, Pageable pageable) {
        return repository.filtrar(criteria, pageable);
    }

    public ProdutoServicoQuery getById(UUID id) {
        return repository.findProjectedById(id);
    }
}
