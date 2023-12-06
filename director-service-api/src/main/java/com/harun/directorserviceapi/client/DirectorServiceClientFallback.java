package com.harun.directorserviceapi.client;


import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.directorserviceapi.dto.DirectorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Fallback for {@link DirectorServiceClient}
 */

@Component
public class DirectorServiceClientFallback implements DirectorServiceClient {

    @Override
    public ResponseEntity<Response> save(DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> update(String id, DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> delete(String id) {
        return ResponseFactory.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> get(String id) {
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(Page.empty(pageable), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> filter(Pageable pageable, DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(Page.empty(pageable), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}