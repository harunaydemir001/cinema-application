package com.harun.actorserviceapi.client;


import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.common.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class ActorServiceClientFallback implements ActorServiceClient {

    @Override
    public ResponseEntity<Response> save(ActorDTO actorDTO) {
        return ResponseFactory.createResponse(actorDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> update(String id, ActorDTO actorDTO) {
        return ResponseFactory.createResponse(actorDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> delete(String id) {
        return ResponseFactory.createResponse(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> get(String id) {
        return ResponseFactory.createResponse(new ActorDTO(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(PageUtil.EmptyPage(pageable), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> filter(Pageable pageable, ActorDTO actorDTO) {
        return ResponseFactory.createResponse(PageUtil.EmptyPage(pageable), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> getAllByMovie(String movieTitle) {
        return ResponseFactory.createResponse(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }
}