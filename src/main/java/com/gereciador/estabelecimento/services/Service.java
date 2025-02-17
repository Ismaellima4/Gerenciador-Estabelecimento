package com.gereciador.estabelecimento.services;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service<E, P> {
    E save(E obj);
    E update(P primaryKey, E obj);
    void delete(P primaryKey);
    E getById(P primaryKey);
    List<E> getAll();
}
