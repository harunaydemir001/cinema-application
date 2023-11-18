package com.harun.common.interfaces;


import com.harun.common.model.Response;
import org.springframework.http.ResponseEntity;

public interface CrudOperationsController<T> {

    ResponseEntity<Response> save(T entityDTO);

    ResponseEntity<Response> update(T entityDTO);

    ResponseEntity<Response> get(Long id);

    ResponseEntity<Response> delete(Long id);
}
