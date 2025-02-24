package com.gereciador.estabelecimento.controllers.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProdutoRequestDTO(String nome, List<Long> idsCategorias, BigDecimal preco, Integer quantidade, List<Long> idsFornecedores, LocalDate validade) {
}
