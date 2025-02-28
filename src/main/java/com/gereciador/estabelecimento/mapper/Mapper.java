package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.exceptions.NotFoundException;

public interface Mapper<DRT, DRS, E> {
    E toEntity(DRS dtoRequest) throws NotFoundException;
    DRT toDTO(E entity);
}
