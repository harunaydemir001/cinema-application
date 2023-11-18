package com.harun.common.base;


import com.harun.common.model.Response;
import org.springframework.http.ResponseEntity;

public interface BaseController<TId, TEntityDTO> {

    ResponseEntity<Response> save(TEntityDTO entityDTO);

    ResponseEntity<Response> update(TEntityDTO entityDTO);

    ResponseEntity<Response> get(TId id);

    ResponseEntity<Response> delete(TId id);
}
