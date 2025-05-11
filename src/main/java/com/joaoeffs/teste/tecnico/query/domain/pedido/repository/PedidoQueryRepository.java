package com.joaoeffs.teste.tecnico.query.domain.pedido.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.joaoeffs.teste.tecnico.query.domain.pedido.criteria.PedidoCriteria;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.PedidoQuery;
import com.joaoeffs.teste.tecnico.query.domain.pedido.model.item.ItemPedidoQuery;

public interface PedidoQueryRepository extends Repository<PedidoQuery, UUID>,
    JpaSpecificationExecutor<PedidoQuery> {

    PedidoQuery findProjectedById(UUID id);

    default Slice<PedidoQuery> filtrar(PedidoCriteria criteria, Pageable pageable) {
        return findAll(criteria, pageable);
    }

    @Query(value = """
            SELECT ip.* FROM item_pedido ip WHERE ip.pedido_id = :id AND deleted = false
        """, nativeQuery = true)
    Slice<ItemPedidoQuery> findItensByPedidoId(UUID id, Pageable pageable);

}
