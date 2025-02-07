package com.gereciador.estabelecimento.entities;

import com.gereciador.estabelecimento.enums.Status;
import com.gereciador.estabelecimento.enums.TipoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagamento_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(nullable = false)
    private BigDecimal valor;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status statusPagamento;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Status getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(Status statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(getId(), pagamento.getId()) && Objects.equals(getPedido(), pagamento.getPedido()) && Objects.equals(getValor(), pagamento.getValor()) && Objects.equals(getData(), pagamento.getData()) && Objects.equals(getCliente(), pagamento.getCliente()) && getTipoPagamento() == pagamento.getTipoPagamento() && getStatusPagamento() == pagamento.getStatusPagamento();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPedido(), getValor(), getData(), getCliente(), getTipoPagamento(), getStatusPagamento());
    }
}
