package com.harun.actorserviceapi.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "actor-service", url = "http://localhost:8082/api/v1/actor", configuration = FeignClientConfiguration.class, fallback = ActorServiceClientFallback.class, primary = false)
public interface ActorServiceClient {

    @PostMapping
    ResponseEntity<Response> save(@RequestBody ActorDTO actorDTO);

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable("id") String id, @RequestBody ActorDTO actorDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Response> delete(@PathVariable("id") String id);

    @GetMapping("/{id}")
    ResponseEntity<Response> get(@PathVariable("id") String id);

    @GetMapping()
    ResponseEntity<Response> getAll(Pageable pageable);

    @PostMapping("/filter")
    ResponseEntity<Response> filter(Pageable pageable, @RequestBody() ActorDTO actorDTO);

    @GetMapping("/by-movie")
    ResponseEntity<Response> getAllByMovie(@RequestParam String movieTitle);
}
