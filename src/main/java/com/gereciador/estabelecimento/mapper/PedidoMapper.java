package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.PedidoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PedidoResponseDTO;
import com.gereciador.estabelecimento.entities.Pedido;

public class PedidoMapper implements Mapper<PedidoResponseDTO, PedidoRequestDTO, Pedido>{

    @Override
    public Pedido toEntity(PedidoRequestDTO dtoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

    @Override
    public PedidoResponseDTO toDTO(Pedido entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDTO'");
    }
    
}
