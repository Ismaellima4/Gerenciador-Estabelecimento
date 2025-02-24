package com.gereciador.estabelecimento.services;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service<DRS, DRT, P> {
    DRS save(DRT obj);
    DRS update(P primaryKey, DRT obj);
    void delete(P primaryKey);
    DRS getById(P primaryKey);
    List<DRS> getAll();
}
