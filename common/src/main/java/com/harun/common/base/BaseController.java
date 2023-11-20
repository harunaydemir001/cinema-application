package com.harun.common.base;


import com.harun.common.model.Response;
import org.springframework.http.ResponseEntity;

public interface BaseController<EntityDTO extends BaseDTO<?>, Id> {

    ResponseEntity<Response> save(EntityDTO entityDTO);

    ResponseEntity<Response> update(Id id, EntityDTO entityDTO);

    ResponseEntity<Response> get(Id id);

    ResponseEntity<Response> delete(Id id);
}
