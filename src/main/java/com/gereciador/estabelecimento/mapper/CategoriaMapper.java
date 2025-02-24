package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.CategoriaRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.CategoriaResponseDTO;
import com.gereciador.estabelecimento.entities.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper implements Mapper<CategoriaResponseDTO, CategoriaRequestDTO, Categoria> {

    @Override
    public Categoria toEntity(CategoriaRequestDTO categoriaDTO) {
        Categoria categoria =  new Categoria();
        categoria.setNome(categoriaDTO.nome());
        return categoria;
    }

    @Override
    public CategoriaResponseDTO toDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
}
