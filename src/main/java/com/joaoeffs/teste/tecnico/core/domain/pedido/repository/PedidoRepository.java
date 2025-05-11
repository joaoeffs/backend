package com.joaoeffs.teste.tecnico.core.domain.pedido.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.Pedido;
import com.joaoeffs.teste.tecnico.infra.exception.PedidoIdNotFoundException;

public interface PedidoRepository extends Repository<Pedido, UUID> {

    void save(Pedido pedido);

    Optional <Pedido> findById(UUID id);

    default Pedido getById(UUID id) {
        return findById(id)
            .orElseThrow(() -> new PedidoIdNotFoundException("Pedido não foi encontrado"));
    }

    @Query("SELECT COUNT(p) > 0 FROM Pedido p INNER JOIN p.itens i INNER JOIN i.produtoServico ps WHERE ps.id = :produtoServico")
    boolean existsByProdutoServicoId(UUID produtoServico);

    @Query("SELECT COUNT(*) > 0 FROM Pedido p WHERE p.numeroPedido = :numeroPedido")
    boolean existsByNumeroPedido(Integer numeroPedido);
}
