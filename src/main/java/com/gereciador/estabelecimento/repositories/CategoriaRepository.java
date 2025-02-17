package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
