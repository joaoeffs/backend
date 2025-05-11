package com.joaoeffs.teste.tecnico.core.domain.pedido.model;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.joaoeffs.teste.tecnico.core.domain.pedido.model.itens.ItemPedido;
import com.joaoeffs.teste.tecnico.core.domain.pedido.model.situacao.Situacao;
import com.joaoeffs.teste.tecnico.infra.ddd.jpa.BaseEntity;
import com.joaoeffs.teste.tecnico.infra.exception.ItemPedidoIdNotFoundException;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoInativoException;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoFechadoException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "pedido")
public class Pedido extends BaseEntity {

    @Id
    private UUID id;

    private String cliente;

    private Integer numeroPedido;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    private BigDecimal percentualDesconto;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id", nullable = false)
    private final Set<ItemPedido> itens = new HashSet<>();

    public static PedidoBuilder builder() {
        return new PedidoBuilder();
    }

    public Pedido(PedidoBuilder builder) {
        this.id = UUID.randomUUID();
        this.cliente = requireNonNull(builder.getCliente());
        this.numeroPedido = requireNonNull(builder.getNumeroPedido());
        this.situacao = Situacao.PENDENTE;
        this.valorTotal = BigDecimal.ZERO;
        this.valorDesconto = BigDecimal.ZERO;
        this.percentualDesconto = BigDecimal.ZERO;
    }

    public void adicionarItem(ItemPedido item) {
        if (this.isFechado())
            throw new PedidoFechadoException("Pedido fechado, não é possível adicionar mais itens.");

        if (item.getProdutoServico().isInativo())
            throw new ProdutoServicoInativoException("Não é possível adicionar um produto/serviço inativo ao pedido.");

        this.itens.add(item);
        this.valorTotal = calcularValorTotal();
        this.aplicarDesconto(this.percentualDesconto);
    }

    public void removerItem(UUID itemPedido) {
        if (this.isFechado())
            throw new PedidoFechadoException("Pedido fechado, não é possível remover os itens.");

        ItemPedido item = this.itens.stream()
            .filter(i -> i.getId().equals(itemPedido))
            .findAny()
            .orElseThrow(() -> new ItemPedidoIdNotFoundException("Item Pedido não foi encontrado"));

        item.remover();
        this.valorTotal = calcularValorTotal();
        this.aplicarDesconto(this.percentualDesconto);
    }

    public void fechar() {
        this.situacao = situacao.fechar(this);
    }

    public void cancelar() {
        this.situacao = situacao.cancelar(this);
    }

    public void aplicarDesconto(BigDecimal desconto) {
        if (this.isFechado())
            throw new PedidoFechadoException("Pedido fechado, não é possível aplicar desconto.");

        this.percentualDesconto = desconto;
        this.valorDesconto = calcularDesconto();
    }

    public boolean isFechado() {
        return Situacao.FECHADO.equals(this.situacao);
    }

    private BigDecimal calcularValorTotal() {
        return this.itens.stream()
            .filter(ItemPedido::nonDeleted)
            .map(ItemPedido::calcularValorTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularDesconto() {
        return this.itens.stream()
            .filter(ItemPedido::nonDeleted)
            .filter(item -> item.getProdutoServico().isProduto())
            .map(item -> item.calcularValorTotalComDesconto(porcentagem(this.percentualDesconto)))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal porcentagem(BigDecimal desconto) {
        if (isNull(desconto))
            return BigDecimal.ZERO;

        return desconto.divide(new BigDecimal(100));
    }
}
