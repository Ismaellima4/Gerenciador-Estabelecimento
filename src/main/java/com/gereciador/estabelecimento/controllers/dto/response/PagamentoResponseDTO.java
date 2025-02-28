package com.gereciador.estabelecimento.controllers.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gereciador.estabelecimento.enums.Status;
import com.gereciador.estabelecimento.enums.TipoPagamento;

public record PagamentoResponseDTO(Long id, BigDecimal valor, LocalDate data, Long clienteID, TipoPagamento tipoPagamento, Status status) {
    
}
