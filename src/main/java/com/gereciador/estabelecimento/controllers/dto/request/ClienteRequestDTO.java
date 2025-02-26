package com.gereciador.estabelecimento.controllers.dto.request;

import java.util.List;

public record ClienteRequestDTO(String nome , String cpf, List<String> contatos) {
}
