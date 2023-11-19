package com.harun.common.base;

public interface BaseService<TEntityDTO, TId> {

    TEntityDTO save(TEntityDTO entityDTO);

    TEntityDTO update(TEntityDTO entityDTO);

    TEntityDTO get(TId id);

    void delete(TId id);
}
