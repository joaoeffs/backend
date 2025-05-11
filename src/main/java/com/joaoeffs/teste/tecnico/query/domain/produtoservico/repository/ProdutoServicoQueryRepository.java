package com.joaoeffs.teste.tecnico.query.domain.produtoservico.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import com.joaoeffs.teste.tecnico.query.domain.produtoservico.criteria.ProdutoServicoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.produtoservico.model.ProdutoServicoQuery;


public interface ProdutoServicoQueryRepository extends Repository<ProdutoServicoQuery, UUID>,
    JpaSpecificationExecutor<ProdutoServicoQuery> {

    ProdutoServicoQuery findProjectedById(UUID id);

    default Slice<ProdutoServicoQuery> filtrar(ProdutoServicoCriteria criteria, Pageable pageable) {
        return findAll(criteria, pageable);
    }
}
