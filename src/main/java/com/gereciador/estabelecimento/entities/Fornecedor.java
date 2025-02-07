package com.gereciador.estabelecimento.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @ElementCollection
    @CollectionTable(name = "tb_contatos", joinColumns = @JoinColumn(name = "tb_fornecedor_id"))
    @Column(nullable = false, unique = true)
    private List<String> contatos;

    @Column(nullable = false)
    private String nome;
}
