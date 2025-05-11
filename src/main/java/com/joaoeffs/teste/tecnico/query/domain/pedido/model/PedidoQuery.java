package com.joaoeffs.teste.tecnico.query.domain.pedido.model;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.joaoeffs.teste.tecnico.query.domain.pedido.model.item.ItemPedidoQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@NoArgsConstructor(access = PRIVATE, force = true)

@Getter
@Entity
@Table(name = "pedido")
public class PedidoQuery {

    @Id
    private UUID id;

    private Integer numeroPedido;

    private String cliente;

    private String situacao;

    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    private BigDecimal percentualDesconto;

    @Column(name = "row_updated_at")
    private LocalDateTime rowUpdatedAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Set<ItemPedidoQuery> itemPedido;
}
