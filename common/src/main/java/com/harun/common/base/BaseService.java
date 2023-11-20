package com.harun.common.base;

public interface BaseService<EntityDTO extends BaseDTO<?>, Id> {

    EntityDTO save(EntityDTO entityDTO);

    EntityDTO update(EntityDTO entityDTO, Id id);

    EntityDTO get(Id id);

    void delete(Id id);
}
