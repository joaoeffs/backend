package com.joaoeffs.teste.tecnico.query.domain.pedido.model.item;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.Where;

import com.joaoeffs.teste.tecnico.query.domain.produtoservico.model.ProdutoServicoQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@NoArgsConstructor

@Getter
@Entity
@Where(clause = "deleted = false")
@Table(name = "item_pedido")
public class ItemPedidoQuery {

    @Id
    private UUID id;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    @ManyToOne
    @JoinColumn(name = "produto_servico_id")
    private ProdutoServicoQuery produtoServico;

}
