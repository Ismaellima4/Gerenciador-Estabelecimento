package com.gereciador.estabelecimento.controllers.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.gereciador.estabelecimento.enums.Status;

public record PedidoRequestDTO(List<Long> idProdutos, LocalDate data, Status status) {
}
