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
}
