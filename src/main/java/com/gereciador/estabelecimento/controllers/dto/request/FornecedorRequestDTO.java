package com.gereciador.estabelecimento.controllers.dto.request;

import java.util.List;

public record FornecedorRequestDTO(String nome , String cnpj, List<String> contatos) {
}
