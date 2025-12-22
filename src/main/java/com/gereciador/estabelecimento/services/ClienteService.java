package com.gereciador.estabelecimento.services;


import com.gereciador.estabelecimento.controllers.dto.request.ClienteRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ClienteResponseDTO;
import com.gereciador.estabelecimento.entities.Cliente;
import com.gereciador.estabelecimento.mapper.ClienteMapper;
import com.gereciador.estabelecimento.repositories.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@org.springframework.stereotype.Service
public class ClienteService implements Service<ClienteResponseDTO, ClienteRequestDTO, Long> {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper = new ClienteMapper();

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDTO save(ClienteRequestDTO obj){
        Cliente cliente = this.clienteRepository.save(this.mapper.toEntity(obj));
        return this.mapper.toDTO(cliente);
    }

    @Override
    public ClienteResponseDTO update(Long primaryKey, ClienteRequestDTO obj) {
        Cliente clienteUpdated = this.clienteRepository.findById(primaryKey).orElseThrow();
        if(obj.nome() != null) clienteUpdated.setNome(obj.nome());
        if (obj.cpf() != null) clienteUpdated.setCpf(obj.cpf());
        clienteUpdated.setContatos(obj.contatos());
        Cliente cliente = this.clienteRepository.save(clienteUpdated);
        return this.mapper.toDTO(cliente);
    }

    @Override
    public ClienteResponseDTO getById(Long primaryKey) {
        Cliente cliente = this.clienteRepository.findById(primaryKey).orElseThrow();
        return this.mapper.toDTO(cliente);
    }

    @Override
    @Transactional
    public void delete(Long primaryKey) {
        this.clienteRepository.deleteById(primaryKey);
    }

    @Override
    public List<ClienteResponseDTO> getAll() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes.stream()
                .map(this.mapper::toDTO).toList();
    }
}
