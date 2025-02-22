package com.gereciador.estabelecimento.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fornecedor_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @ElementCollection
    @CollectionTable(name = "tb_fornecedores_contatos", joinColumns = @JoinColumn(name = "fornecedor_id"))
    @Column(nullable = false, unique = true)
    private List<String> contatos;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "fornecedores")
    private List<Produto> produtos;

    public Fornecedor() {
    }

    @Column(nullable = false, length = 60)
    private String nome;

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<String> getContatos() {
        return contatos;
    }

    public void setContatos(List<String> contatos) {
        this.contatos = contatos;
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

    public void addContato(String contato){
        this.contatos.add(contato);
    }

    public void removeContato(String contato){
        this.contatos.remove(contato);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCnpj(), that.getCnpj()) && Objects.equals(getContatos(), that.getContatos()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCnpj(), getContatos(), getNome());
    }
}
