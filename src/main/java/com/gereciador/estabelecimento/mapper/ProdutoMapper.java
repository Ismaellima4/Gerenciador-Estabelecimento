package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.ProdutoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.CategoriaResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.FornecedorResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ProdutoResponseDTO;
import com.gereciador.estabelecimento.entities.Produto;
import com.gereciador.estabelecimento.repositories.CategoriaRepository;
import com.gereciador.estabelecimento.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoMapper implements Mapper<ProdutoResponseDTO, ProdutoRequestDTO, Produto>{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private FornecedorMapper fornecedorMapper;

    @Override
    public Produto toEntity(ProdutoRequestDTO dtoRequest) {
        Produto produto =  new Produto();
        produto.setNome(dtoRequest.nome());
        produto.setFornecedores(this.fornecedorRepository.findAllById(dtoRequest.idsFornecedores()));
        produto.setCategorias(this.categoriaRepository.findAllById(dtoRequest.idsCategorias()));
        produto.setValidate(dtoRequest.validade());
        produto.setPreco(dtoRequest.preco());
        produto.setQuantidade(dtoRequest.quantidade());
        return produto;
    }

    @Override
    public ProdutoResponseDTO toDTO(Produto entity) {
        List<CategoriaResponseDTO> categoriaResponseDTOS = entity.getCategorias().stream().map(this.categoriaMapper::toDTO).toList();
        List<FornecedorResponseDTO> fornecedorResponseDTOS = entity.getFornecedores().stream().map(this.fornecedorMapper::toDTO).toList();
        return new ProdutoResponseDTO(entity.getNome(), categoriaResponseDTOS, entity.getPreco(), entity.getQuantidade(), fornecedorResponseDTOS, entity.getValidate());
    }
}
