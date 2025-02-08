package com.gereciador.estabelecimento.repositories;

import java.util.List;

public interface Repository<E, P> {  E save(E obj);
    E update(P primaryKey, E obj);
    void delete(P primaryKey);
    E getById(P primaryKey);
    List<E> getAll();
}
