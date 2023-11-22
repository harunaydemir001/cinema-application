package com.harun.common.base;


import com.harun.common.model.Response;
import org.springframework.http.ResponseEntity;

public interface BaseController<E extends BaseDTO<?>, I> {

    ResponseEntity<Response> save(E entityDTO);

    ResponseEntity<Response> update(I id, E entityDTO);

    ResponseEntity<Response> get(I id);

    ResponseEntity<Response> delete(I id);
}
