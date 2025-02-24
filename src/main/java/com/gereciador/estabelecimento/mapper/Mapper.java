package com.gereciador.estabelecimento.mapper;

public interface Mapper<DRT, DRS, E> {
    E toEntity(DRS dtoRequest);
    DRT toDTO(E entity);
}
