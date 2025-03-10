package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.FornecedorRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.FornecedorResponseDTO;
import com.gereciador.estabelecimento.entities.Fornecedor;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper implements Mapper<FornecedorResponseDTO, FornecedorRequestDTO, Fornecedor> {

    @Override
    public Fornecedor toEntity(FornecedorRequestDTO fornecedorDTO){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setContatos(fornecedorDTO.contatos());
        return fornecedor;
    }

    @Override
    public FornecedorResponseDTO toDTO(Fornecedor fornecedor){
        return new FornecedorResponseDTO(fornecedor.getId(),fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getContatos());
    }
}
