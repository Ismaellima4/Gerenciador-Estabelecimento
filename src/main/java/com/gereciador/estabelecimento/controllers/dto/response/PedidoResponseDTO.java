package com.gereciador.estabelecimento.controllers.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.gereciador.estabelecimento.enums.Status;

public record PedidoResponseDTO(Long id, List<ItemPedidoResponseDTO> itensPedido, LocalDate data, Status status) {
    
}
