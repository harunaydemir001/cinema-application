package com.harun.movieserviceapi.client;


import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.movieserviceapi.dto.MovieAndActorDTO;
import com.harun.movieserviceapi.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Component
public class MovieServiceClientFallback implements MovieServiceClient {

    @Override
    public ResponseEntity<Response> save(MovieDTO movieDTO) {
        return ResponseFactory.createResponse(movieDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> update(String id, MovieDTO movieDTO) {
        return ResponseFactory.createResponse(movieDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> delete(String id) {
        return ResponseFactory.createResponse(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> get(String id) {
        MovieDTO movieDTO = new MovieDTO();
        return ResponseFactory.createResponse(movieDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(Page.empty(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> filter(Pageable pageable, MovieDTO movieDTO) {
        return ResponseFactory.createResponse(Page.empty(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> combineFilmAndActors(String title) {
        MovieAndActorDTO movieAndActorDTO = new MovieAndActorDTO();
        return ResponseFactory.createResponse(movieAndActorDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Response> getAllData() {
        return ResponseFactory.createResponse(Map.of(), HttpStatus.NOT_FOUND);
    }

}