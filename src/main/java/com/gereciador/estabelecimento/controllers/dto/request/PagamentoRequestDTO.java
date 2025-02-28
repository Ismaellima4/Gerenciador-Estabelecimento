package com.gereciador.estabelecimento.controllers.dto.request;

import java.time.LocalDate;

public record PagamentoRequestDTO(Long idPedido, LocalDate data, Long idCliente) {
    
}
