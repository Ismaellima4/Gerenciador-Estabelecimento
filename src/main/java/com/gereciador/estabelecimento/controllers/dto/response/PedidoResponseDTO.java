package com.gereciador.estabelecimento.controllers.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.gereciador.estabelecimento.entities.Pagamento;
import com.gereciador.estabelecimento.enums.Status;

public record PedidoResponseDTO(Long id, List<ProdutoResponseDTO> produtos, LocalDate data, Status status, Pagamento pagamento) {
    
}
