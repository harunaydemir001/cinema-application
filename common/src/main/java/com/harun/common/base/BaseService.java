package com.harun.common.base;

public interface BaseService<E extends BaseDTO<?>, I> {

    E save(E entityDTO);

    E update(E entityDTO, I id);

    E get(I id);

    void delete(I id);
}
