package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.ClienteRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ClienteResponseDTO;
import com.gereciador.estabelecimento.entities.Cliente;

public class ClienteMapper implements Mapper<ClienteResponseDTO, ClienteRequestDTO, Cliente> {

    @Override
    public Cliente toEntity(ClienteRequestDTO clienteDTO){
        Cliente cliente =  new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setCpf(clienteDTO.cpf());
        return cliente;
    }

    @Override
    public ClienteResponseDTO toDTO(Cliente cliente){
        return new ClienteResponseDTO(cliente.getId(),cliente.getNome(), cliente.getCpf(), cliente.getContatos(), cliente.getPagamentos());
    }
}
