package com.gereciador.estabelecimento.controllers.dto.response;

import com.gereciador.estabelecimento.entities.Pagamento;

import java.util.List;

public record ClienteResponseDTO(Long id, String nome, String cpf, List<String> contatos, List<Pagamento> pagamentos) {
}
