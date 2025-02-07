package com.gereciador.estabelecimento.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_produto_categoria",
        joinColumns = @JoinColumn(name = "tb_produtos_id"),
        inverseJoinColumns = @JoinColumn(name = "tb_categorias_id")
    )
    private List<Categoria> categorias;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToMany
    @JoinTable(name = "tb_produto_fornecedor",
            joinColumns = @JoinColumn(name = "tb_produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "tb_fornecedores_id")
    )
    private List<Fornecedor> fornecedores;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate validate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public LocalDate getValidate() {
        return validate;
    }

    public void setValidate(LocalDate validate) {
        this.validate = validate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId()) && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getCategorias(), produto.getCategorias()) && Objects.equals(getPreco(), produto.getPreco()) && Objects.equals(getQuantidade(), produto.getQuantidade()) && Objects.equals(getFornecedores(), produto.getFornecedores()) && Objects.equals(getValidate(), produto.getValidate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCategorias(), getPreco(), getQuantidade(), getFornecedores(), getValidate());
    }
}
