package com.gereciador.estabelecimento.entities;

import com.gereciador.estabelecimento.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_pedido_produto",
            joinColumns = @JoinColumn(name = "tb_pedidos_id"),
            inverseJoinColumns = @JoinColumn(name = "tb_produtos_id")
    )
    private List<Produto> produtos;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status statusPedido;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public Long getId() {
        return id;
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
}
