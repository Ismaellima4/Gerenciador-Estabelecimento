package com.gereciador.estabelecimento.controllers.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gereciador.estabelecimento.enums.Status;
import com.gereciador.estabelecimento.enums.TipoPagamento;

public record PagamentoRequestDTO(Long idPedido, BigDecimal valor, LocalDate data, Long idCliente, TipoPagamento tipoPagamento, Status status) {
    
}
