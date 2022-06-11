package com.dreamteam3.data.mapper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    D toDTO(E entity);

}
