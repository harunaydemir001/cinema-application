package com.harun.movieserviceapi.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.model.Response;
import com.harun.movieserviceapi.dto.MovieDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "movie-service", url = "http://localhost:8080/api/v1/movie", configuration = FeignClientConfiguration.class, fallback = MovieServiceClientFallback.class, primary = false)
public interface MovieServiceClient {

    @PostMapping
    ResponseEntity<Response> save(@RequestBody MovieDTO movieDTO);

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable("id") String id, @RequestBody MovieDTO movieDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> delete(@PathVariable("id") String id);

    @GetMapping("/{id}")
    ResponseEntity<Response> get(@PathVariable("id") String id);

    @GetMapping()
    ResponseEntity<Response> getAll(Pageable pageable);

    @PostMapping("/filter")
    ResponseEntity<Response> filter(Pageable pageable, @RequestBody() MovieDTO movieDTO);

    @GetMapping("/combine-actors")
    ResponseEntity<Response> combineFilmAndActors(@ApiParam(value = "Movie Title") @RequestParam() String title);

    @GetMapping("/all-data")
    ResponseEntity<Response> getAllData();
}
