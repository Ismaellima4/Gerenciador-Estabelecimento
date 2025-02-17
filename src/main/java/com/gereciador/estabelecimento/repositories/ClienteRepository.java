package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}


