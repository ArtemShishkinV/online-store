package com.shishkin.auctionapp.repository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<T, ID> {
    Iterable<T> findAll();

    Optional<T> findById(ID id);

    Iterable<T> saveAll(List<T> list);

    T save(T entity);

    void deleteById(ID id);
}
