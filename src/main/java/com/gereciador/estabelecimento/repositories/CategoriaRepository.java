package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findCategoriaByNome(String nome);
}
