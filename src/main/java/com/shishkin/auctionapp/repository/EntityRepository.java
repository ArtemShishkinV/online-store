package com.shishkin.auctionapp.repository;

import java.util.Optional;

public interface EntityRepository<T, ID> {
    Iterable<T> findAll();

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
