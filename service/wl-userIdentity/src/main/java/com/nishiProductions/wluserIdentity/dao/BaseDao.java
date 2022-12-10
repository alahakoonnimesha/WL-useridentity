package com.nishiProductions.wluserIdentity.dao;

import java.util.List;

public interface BaseDao<T> {

    void create(T type);

    T merge(T type);

    T read(Long id);

    List<T> readAll();

    void update(T type);

    void delete(T type);

    T saveOrUpdate(T type);
}

