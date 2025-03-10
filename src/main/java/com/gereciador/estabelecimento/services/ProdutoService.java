package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.controllers.dto.request.ProdutoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ProdutoResponseDTO;
import com.gereciador.estabelecimento.entities.Categoria;
import com.gereciador.estabelecimento.entities.Fornecedor;
import com.gereciador.estabelecimento.entities.Produto;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.mapper.ProdutoMapper;
import com.gereciador.estabelecimento.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class ProdutoService implements Service<ProdutoResponseDTO, ProdutoRequestDTO, Long>{

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO save(ProdutoRequestDTO obj) {
        Produto produto = this.produtoRepository.save(this.produtoMapper.toEntity(obj));
        return this.produtoMapper.toDTO(produto);
    }

    @Override
    public ProdutoResponseDTO update(Long primaryKey, ProdutoRequestDTO obj) throws NotFoundException {
        Produto produtoUpdating = this.produtoRepository.findById(primaryKey).orElseThrow(() -> new NotFoundException("Produto not found id " + primaryKey));
        //List<Categoria> categorias = produtoRequest.getCategorias();
        //List<Fornecedor> fornecedors = produtoRequest.getFornecedores();

        if (obj.nome() != null) produtoUpdating.setNome(obj.nome());
        if (obj.preco() != null) produtoUpdating.setPreco(obj.preco());
        if (obj.quantidade() != null) produtoUpdating.setQuantidade(obj.quantidade());
        if (obj.validade() != null) produtoUpdating.setQuantidade(obj.quantidade());
//        if (categorias != null && !categorias.isEmpty()) {
//            List<Categoria> setCategorias = Stream.concat(produtoRequest.getCategorias().stream(), categorias.stream()).distinct().toList();
//            produtoUpdating.setCategorias(setCategorias);
//        }
//        if (fornecedors != null && !fornecedors.isEmpty()) {
//            List<Fornecedor> setFornecedores = Stream.concat(produtoRequest.getFornecedores().stream(), fornecedors.stream()).distinct().toList();
//            produtoUpdating.setFornecedores(setFornecedores);
//        }

        Produto produto = this.produtoRepository.save(produtoUpdating);
        return this.produtoMapper.toDTO(produto);
    }

    @Override
    public void delete(Long primaryKey) {
        this.produtoRepository.deleteById(primaryKey);
    }

    @Override
    public ProdutoResponseDTO getById(Long primaryKey) throws NotFoundException {
        Produto produto = this.produtoRepository.findById(primaryKey).orElseThrow(() -> new NotFoundException("Produto not found id " + primaryKey));
        return this.produtoMapper.toDTO(produto);
    }

    @Override
    public List<ProdutoResponseDTO> getAll() {
        List<Produto> produtos = this.produtoRepository.findAll();
        return produtos.stream()
                .map(this.produtoMapper::toDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> getAllPage(int page, int size) {
        Page<Produto> produtos = this.produtoRepository.findAll(PageRequest.of(page, size));
        return produtos.stream().map(this.produtoMapper::toDTO).toList();
    }
}
