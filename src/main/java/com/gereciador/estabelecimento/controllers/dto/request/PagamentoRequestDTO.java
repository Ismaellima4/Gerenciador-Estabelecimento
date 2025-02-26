package com.gereciador.estabelecimento.controllers.dto.request;

import java.time.LocalDate;

import com.gereciador.estabelecimento.enums.Status;
import com.gereciador.estabelecimento.enums.TipoPagamento;

public record PagamentoRequestDTO(Long idPedido, LocalDate data, Long idCliente, TipoPagamento tipoPagamento, Status status) {
    
}
