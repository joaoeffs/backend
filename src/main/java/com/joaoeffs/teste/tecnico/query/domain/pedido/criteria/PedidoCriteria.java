package com.joaoeffs.teste.tecnico.query.domain.pedido.criteria;

import static java.util.Objects.nonNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.joaoeffs.teste.tecnico.query.domain.pedido.model.PedidoQuery;

import lombok.Getter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Getter
public class PedidoCriteria implements Specification<PedidoQuery> {

    @Serial
    private static final long serialVersionUID = -7900086447976604730L;

    private final String cliente;

    private final Integer numeroPedido;

    private final List<String> situacoes;

    public PedidoCriteria(String cliente, Integer numeroPedido, List<String> situacoes) {
        this.cliente = cliente;
        this.numeroPedido = numeroPedido;
        this.situacoes = situacoes;
    }

    @Override
    public Predicate toPredicate(Root<PedidoQuery> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return toAdvancedSearchPredicate(root, query, criteriaBuilder);
    }

    private Predicate toAdvancedSearchPredicate(Root<PedidoQuery> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(cliente))
            predicates.add(builder.like(builder.lower(root.get("cliente")), "%" + cliente.toLowerCase() + "%"));

        if (nonNull(numeroPedido))
            predicates.add(builder.equal(root.get("numeroPedido"), numeroPedido));

        if (nonNull(situacoes) && !situacoes.isEmpty())
            predicates.add(root.get("situacao").in(situacoes));

        query.orderBy(builder.desc(root.get("numeroPedido")),
            builder.asc(root.get("rowUpdatedAt")));

        if (predicates.isEmpty())
            return builder.conjunction();

        return builder.and(predicates.toArray(new Predicate[1]));
    }
}
