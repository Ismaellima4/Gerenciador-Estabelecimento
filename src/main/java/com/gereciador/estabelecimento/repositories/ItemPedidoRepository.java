package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Optional<ItemPedido> findItemByProdutoId(Long primaryKey);
}
