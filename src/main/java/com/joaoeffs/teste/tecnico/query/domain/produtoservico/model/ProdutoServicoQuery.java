package com.joaoeffs.teste.tecnico.query.domain.produtoservico.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor

@Getter
@Entity
@Table(name = "produto_servico")
public class ProdutoServicoQuery {

    @Id
    private UUID id;

    private String descricao;

    private String tipo;

    private BigDecimal preco;

    private String situacao;

    @Column(name = "row_updated_at")
    private LocalDateTime rowUpdatedAt;
}
