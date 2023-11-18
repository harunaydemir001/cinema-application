package com.harun.common.interfaces;

public interface CrudOperationsService<T> {

    T save(T entityDTO);

    T update(T entityDTO);

    T get(Long id);

    void delete(Long id);
}
