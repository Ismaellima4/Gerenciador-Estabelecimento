package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
