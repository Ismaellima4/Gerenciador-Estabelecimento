package com.gereciador.estabelecimento.controllers.dto.request;

import java.util.List;

public record PedidoRequestDTO(List<ItemPedidoRequestDTO> itensPedido) {
}
