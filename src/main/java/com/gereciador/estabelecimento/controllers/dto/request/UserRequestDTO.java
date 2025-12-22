package com.gereciador.estabelecimento.controllers.dto.request;

import com.gereciador.estabelecimento.enums.UserRole;

public record UserRequestDTO(String username, String password, UserRole role) {
}
