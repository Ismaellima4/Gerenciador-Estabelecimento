package com.gereciador.estabelecimento.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "categorias")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(getId(), categoria.getId()) && Objects.equals(getNome(), categoria.getNome()) && Objects.equals(getProdutos(), categoria.getProdutos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getProdutos());
    }
}
