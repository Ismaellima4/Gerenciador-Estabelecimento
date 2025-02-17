package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.entities.Cliente;
import com.gereciador.estabelecimento.repositories.ClienteRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ClienteService implements Service<Cliente,Long>{

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(Cliente obj) {
       return clienteRepository.save(obj);
    }

    @Override
    public Cliente update(Long primaryKey, Cliente obj) {
        return null;
    }

    @Override
    public void delete(Long primaryKey) {
       clienteRepository.deleteById(primaryKey);
    }

    @Override
    public Cliente getById(Long primaryKey) {
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}
