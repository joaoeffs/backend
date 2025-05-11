package com.joaoeffs.teste.tecnico.query.domain.produtoservico.criteria;

import static java.util.Objects.nonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.joaoeffs.teste.tecnico.query.domain.produtoservico.model.ProdutoServicoQuery;

import lombok.Getter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Getter
public class ProdutoServicoCriteria implements Specification<ProdutoServicoQuery> {

    @Serial
    private static final long serialVersionUID = 8787184181523164092L;

    private final String descricao;

    private final String tipo;

    private final String situacao;

    private final BigDecimal preco;

    public ProdutoServicoCriteria(String descricao, String tipo, String situacao, BigDecimal preco) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.situacao = situacao;
        this.preco = preco;
    }

    @Override
    public Predicate toPredicate(Root<ProdutoServicoQuery> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return toAdvancedSearchPredicate(root, query, builder);
    }

    private Predicate toAdvancedSearchPredicate(Root<ProdutoServicoQuery> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(descricao))
            predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%"));

        if (nonNull(tipo))
            predicates.add(builder.equal(root.get("tipo"), tipo));

        if (nonNull(situacao))
            predicates.add(builder.equal(root.get("situacao"), situacao));

        if (nonNull(preco))
            predicates.add(builder.equal(root.get("preco"), preco));

        query.orderBy(builder.asc(root.get("rowUpdatedAt")));

        if (predicates.isEmpty())
            return builder.conjunction();

        return builder.and(predicates.toArray(new Predicate[1]));
    }
}
