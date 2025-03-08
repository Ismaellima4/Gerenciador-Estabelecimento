package com.gereciador.estabelecimento.services;

import java.nio.channels.NotYetBoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.gereciador.estabelecimento.controllers.dto.request.PedidoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PagamentoResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PedidoResponseDTO;
import com.gereciador.estabelecimento.entities.*;
import com.gereciador.estabelecimento.enums.Status;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.mapper.PedidoMapper;
import com.gereciador.estabelecimento.repositories.PedidoRepository;

@org.springframework.stereotype.Service
public class PedidoService implements Service<PedidoResponseDTO, PedidoRequestDTO, Long> {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;

    public PedidoService(PedidoRepository repository, PedidoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PedidoResponseDTO save(PedidoRequestDTO obj) throws NotFoundException {
        Pedido pedido = this.repository.save(this.mapper.toEntity(obj));
        return this.mapper.toDTO(pedido);
    }

    @Override
    public PedidoResponseDTO update(Long primaryKey, PedidoRequestDTO obj) throws NotFoundException {
        Pedido pedido = this.repository.findById(primaryKey).orElseThrow(() -> new NotFoundException("pedido not found com id " + primaryKey));

        Pedido pedidoRequest = this.mapper.toEntity(obj);

        if(pedidoRequest.getItensPedido() != null) {
            List<ItemPedido> setItens = Stream.concat(pedidoRequest.getItensPedido().stream(), pedido.getItensPedido().stream()).distinct().toList();
            pedido.setItensPedido(setItens);
        }

        if(pedidoRequest.getData() != null) pedido.setData(pedidoRequest.getData());
        if(pedidoRequest.getStatusPedido() != null) pedido.setStatusPedido(pedidoRequest.getStatusPedido());
        if(pedidoRequest.getPagamento() != null) pedido.setPagamento(pedidoRequest.getPagamento());

        Pedido pedidoUpdating = this.repository.save(pedido);
        return this.mapper.toDTO(pedidoUpdating);
    }

    @Override
    public void delete(Long primaryKey) {
        this.repository.deleteById(primaryKey);
    }

    @Override
    public PedidoResponseDTO getById(Long primaryKey) throws NotFoundException {
        Pedido pedido = this.repository.findById(primaryKey).orElseThrow(() -> new NotFoundException("pedido not found id " + primaryKey));
        return this.mapper.toDTO(pedido);
    }

    @Override
    public List<PedidoResponseDTO> getAll() {
        List<Pedido> pedidos = this.repository.findAll();
        return pedidos.stream().map(this.mapper::toDTO).toList();  
    }
}
