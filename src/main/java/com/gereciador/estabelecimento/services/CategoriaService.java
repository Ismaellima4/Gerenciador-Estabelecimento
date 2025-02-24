package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.controllers.dto.request.CategoriaRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.CategoriaResponseDTO;
import com.gereciador.estabelecimento.entities.Categoria;
import com.gereciador.estabelecimento.mapper.CategoriaMapper;
import com.gereciador.estabelecimento.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class CategoriaService implements Service<CategoriaResponseDTO, CategoriaRequestDTO, Long> {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper = new CategoriaMapper();

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO save(CategoriaRequestDTO obj) {
        Categoria categoria = this.repository.save(this.mapper.toEntity(obj));
        return this.mapper.toDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO update(Long primaryKey, CategoriaRequestDTO obj) {
        Categoria categoriaUpdated = this.repository.findById(primaryKey).orElseThrow();
        if (obj.nome() != null) categoriaUpdated.setNome(obj.nome());
        Categoria categoria = this.repository.save(categoriaUpdated);
        return this.mapper.toDTO(categoria);
    }

    @Override
    @Transactional
    public void delete(Long primaryKey) {
        this.repository.deleteById(primaryKey);
    }

    @Override
    public CategoriaResponseDTO getById(Long primaryKey) {
        Categoria categoria = this.repository.findById(primaryKey).orElseThrow();
        return this.mapper.toDTO(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> getAll() {
        List<Categoria> categorias = this.repository.findAll();
        return categorias.stream()
                .map(this.mapper::toDTO)
                .toList();
    }
}
