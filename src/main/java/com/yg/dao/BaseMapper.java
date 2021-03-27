package com.yg.dao;

import java.util.List;

public interface BaseMapper<T> {

    Integer save(T entity);

    Integer delete(Integer id);

    Integer update(T entity);

    T get(Integer id);

    List<T> findAll();

}
