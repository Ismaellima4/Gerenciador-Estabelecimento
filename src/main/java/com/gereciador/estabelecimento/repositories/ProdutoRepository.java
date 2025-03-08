package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Override
    Page<Produto> findAll(Pageable pageable);
}
