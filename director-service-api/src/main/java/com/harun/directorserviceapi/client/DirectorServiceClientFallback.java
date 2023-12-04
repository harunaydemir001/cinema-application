package com.harun.directorserviceapi.client;


import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.common.util.PageUtil;
import com.harun.directorserviceapi.dto.DirectorDTO;
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
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> update(String id, DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> delete(String id) {
        return ResponseFactory.createResponse(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> get(String id) {
        return ResponseFactory.createResponse(new DirectorDTO(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(PageUtil.emptyPage(pageable), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> filter(Pageable pageable, DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(PageUtil.emptyPage(pageable), HttpStatus.NOT_FOUND);
    }
}