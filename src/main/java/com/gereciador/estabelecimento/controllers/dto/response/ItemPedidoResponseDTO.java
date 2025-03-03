package com.gereciador.estabelecimento.controllers.dto.response;

public record ItemPedidoResponseDTO(Long id, ProdutoResponseDTO responseDTO, Integer quantidade) {
}
