package com.gereciador.estabelecimento.controllers.dto.response;

import java.util.List;

public record FornecedorResponseDTO(Long id, String nome, String cnpj, List<String> contatos) {
}
