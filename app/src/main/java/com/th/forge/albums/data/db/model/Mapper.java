package com.th.forge.albums.data.db.model;

public interface Mapper<E, D> {
    D mapFromEntity(E type);

    E mapToEntity(D type);
}
