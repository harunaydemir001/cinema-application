package com.harun.movieserviceapi.service;

import com.harun.common.configuration.PageResponseConfig;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.common.util.JsonUtil;
import com.harun.movieserviceapi.client.MovieServiceClient;
import com.harun.movieserviceapi.dto.MovieAndActorDTO;
import com.harun.movieserviceapi.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service for {@link MovieServiceClient}
 */
@Service
public class MovieServiceClientService {
    private final MovieServiceClient movieServiceClient;

    public MovieServiceClientService(@Qualifier("com.harun.movieserviceapi.client.MovieServiceClient") MovieServiceClient movieServiceClient) {
        this.movieServiceClient = movieServiceClient;
    }

    public MovieDTO save(MovieDTO movieDTO) {
        ResponseEntity<Response> response = movieServiceClient.save(movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), MovieDTO.class);
    }

    public MovieDTO update(String id, MovieDTO movieDTO) {
        ResponseEntity<Response> response = movieServiceClient.update(id, movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), MovieDTO.class);
    }

    public ResponseEntity<Response> delete(String id) {
        movieServiceClient.delete(id);
        return ResponseFactory.createResponse(HttpStatus.OK);
    }

    public MovieDTO get(String id) {
        ResponseEntity<Response> response = movieServiceClient.get(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), MovieDTO.class);
    }

    public Page<MovieDTO> getAll(Pageable pageable) {
        ResponseEntity<Response> response = movieServiceClient.getAll(pageable);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }

    public Page<MovieDTO> filter(Pageable pageable, MovieDTO movieDTO) {
        ResponseEntity<Response> response = movieServiceClient.filter(pageable, movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }

    public MovieAndActorDTO combineFilmAndActors(String title) {
        ResponseEntity<Response> response = movieServiceClient.combineFilmAndActors(title);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), MovieAndActorDTO.class);
    }
}
