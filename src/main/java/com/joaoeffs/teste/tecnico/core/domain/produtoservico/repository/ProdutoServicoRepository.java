package com.joaoeffs.teste.tecnico.core.domain.produtoservico.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoIdNotFoundException;

public interface ProdutoServicoRepository extends Repository<ProdutoServico, UUID> {

    void save(ProdutoServico produtoServico);

    Optional<ProdutoServico> findById(UUID id);

    default ProdutoServico getById(UUID id) {
        return findById(id)
            .orElseThrow(() -> new ProdutoServicoIdNotFoundException("Produto/Servico não foi encontrado"));

    }

    void deleteById(UUID id);
}
