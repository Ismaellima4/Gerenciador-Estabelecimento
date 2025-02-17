package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
