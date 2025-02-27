package com.gereciador.estabelecimento.entities;

import com.gereciador.estabelecimento.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_pedido_produto",
            joinColumns = @JoinColumn(name = "tb_pedidos"),
            inverseJoinColumns = @JoinColumn(name = "tb_produtos")
    )
    private List<Produto> produtos;

    @Temporal(TemporalType.DATE)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Status statusPedido;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public Pedido() {
        this.setStatusPedido(Status.INICIALIZADO);
        this.setData(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Status getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(Status statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this.id) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId()) && Objects.equals(getProdutos(), pedido.getProdutos()) && Objects.equals(getData(), pedido.getData()) && getStatusPedido() == pedido.getStatusPedido() && Objects.equals(getPagamento(), pedido.getPagamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProdutos(), getData(), getStatusPedido(), getPagamento());
    }
}
