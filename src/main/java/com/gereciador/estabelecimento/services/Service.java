package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.exceptions.NotFoundException;

import java.util.List;

public interface Service<DRS, DRT, P> {
    DRS save(DRT obj) throws NotFoundException;
    DRS update(P primaryKey, DRT obj) throws NotFoundException;
    void delete(P primaryKey);
    DRS getById(P primaryKey) throws NotFoundException;
    List<DRS> getAll();
}
