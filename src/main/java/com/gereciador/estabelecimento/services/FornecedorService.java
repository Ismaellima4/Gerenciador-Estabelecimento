package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.controllers.dto.request.FornecedorRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.FornecedorResponseDTO;
import com.gereciador.estabelecimento.entities.Fornecedor;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.mapper.FornecedorMapper;
import com.gereciador.estabelecimento.repositories.FornecedorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class FornecedorService implements  Service<FornecedorResponseDTO, FornecedorRequestDTO, Long>{

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper = new FornecedorMapper();

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    @Transactional
    public FornecedorResponseDTO save(FornecedorRequestDTO obj){
        Fornecedor fornecedor = this.fornecedorRepository.save(this.mapper.toEntity(obj));
        return this.mapper.toDTO(fornecedor);
    }

    @Override
    public FornecedorResponseDTO update(Long primaryKey, FornecedorRequestDTO obj) throws NotFoundException {
        Fornecedor fornecedorUpdated = this.fornecedorRepository.findById(primaryKey).orElseThrow(() -> new NotFoundException("Fornecedor not found id " + primaryKey));
        if(obj.nome() != null) fornecedorUpdated.setNome(obj.nome());
        if (obj.cnpj() != null) fornecedorUpdated.setCnpj(obj.cnpj());
        if (obj.contatos() != null) {
            List<String> contatos =  fornecedorUpdated.getContatos();
            contatos.addAll(obj.contatos());
            fornecedorUpdated.setContatos(contatos);
        }
        Fornecedor fornecedor = this.fornecedorRepository.save(fornecedorUpdated);
        return this.mapper.toDTO(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long primaryKey) {
        this.fornecedorRepository.deleteById(primaryKey);
    }

    @Override
    public FornecedorResponseDTO getById(Long primaryKey) throws NotFoundException {
        Fornecedor fornecedor = this.fornecedorRepository.findById(primaryKey).orElseThrow(() -> new NotFoundException("Fornecedor not found id " + primaryKey));
        return this.mapper.toDTO(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> getAll() {
        List<Fornecedor> fornecedor = this.fornecedorRepository.findAll();
        return fornecedor.stream()
                .map(this.mapper::toDTO)
                .toList();
    }
}
