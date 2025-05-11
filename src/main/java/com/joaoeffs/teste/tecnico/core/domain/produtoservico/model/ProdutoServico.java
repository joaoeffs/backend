package com.joaoeffs.teste.tecnico.core.domain.produtoservico.model;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.UUID;

import com.joaoeffs.teste.tecnico.infra.ddd.jpa.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "produto_servico")
public class ProdutoServico extends BaseEntity {

    @Id
    private UUID id;

    private String descricao;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public static ProdutoServicoBuilder builder() {
        return new ProdutoServicoBuilder();
    }

    public ProdutoServico(ProdutoServicoBuilder builder) {
        this.id = UUID.randomUUID();
        this.descricao = requireNonNull(builder.getDescricao());
        this.tipo = requireNonNull(builder.getTipo());
        this.preco = builder.getPreco();
        this.situacao = Situacao.ATIVO;
    }

    public ProdutoServicoBuilderUpdate alterar() {
        return new ProdutoServicoBuilderUpdate(id, form -> {
            this.preco = requireNonNull(form.getPreco());
            this.descricao = requireNonNull(form.getDescricao());
        });
    }

    public void inativar() {
        this.situacao = Situacao.INATIVO;
    }

    public void ativar() {
        this.situacao = Situacao.ATIVO;
    }

    public boolean isProduto() {
        return Tipo.PRODUTO.equals(tipo);
    }

    public boolean isInativo() {
        return Situacao.INATIVO.equals(situacao);
    }
}
