package com.gereciador.estabelecimento.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    private String cpf;

    @ElementCollection
    @CollectionTable(name = "tb_cliente_contatos", joinColumns = @JoinColumn(name = "tb_clientes_id"))
    @Column(nullable = false, unique = true)
    private List<String> contatos;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getContatos() {
        return contatos;
    }

    public void setContatos(List<String> contatos) {
        this.contatos = contatos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId()) && Objects.equals(getNome(), cliente.getNome()) && Objects.equals(getCpf(), cliente.getCpf()) && Objects.equals(getContatos(), cliente.getContatos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf(), getContatos());
    }
}