package com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.Where;

import com.joaoeffs.teste.tecnico.core.domain.produtoservico.model.ProdutoServico;
import com.joaoeffs.teste.tecnico.infra.ddd.jpa.BaseEntitySoftDeleted;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "item_pedido")
@Where(clause = "deleted=false")
public class ItemPedido extends BaseEntitySoftDeleted {

    @Id
    private UUID id;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    @ManyToOne
    @JoinColumn(name = "produto_servico_id")
    private ProdutoServico produtoServico;

    public static ItemPedidoBuilder builder() {
        return new ItemPedidoBuilder();
    }

    public ItemPedido(ItemPedidoBuilder builder) {
        this.id = UUID.randomUUID();
        this.quantidade = requireNonNull(builder.getQuantidade());
        this.valorUnitario = requireNonNull(builder.getValorUnitario());
        this.produtoServico = requireNonNull(builder.getProdutoServico());
    }

    public void remover() {
        markAsDeleted();
    }

    public BigDecimal calcularValorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal calcularValorTotalComDesconto(BigDecimal desconto) {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade).multiply(desconto));
    }

    public boolean nonDeleted() {
        return !this.deleted;
    }
}
