package com.harun.directorserviceapi.client;

import com.harun.common.configuration.FeignClientConfiguration;
import com.harun.common.model.Response;
import com.harun.directorserviceapi.dto.DirectorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "director-service", url = "http://localhost:8084/api/v1/director", configuration = FeignClientConfiguration.class, fallback = DirectorServiceClientFallback.class, primary = false)
public interface DirectorServiceClient {

    @PostMapping
    ResponseEntity<Response> save(@RequestBody DirectorDTO directorDTO);

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable("id") String id, @RequestBody DirectorDTO directorDTO);
    @DeleteMapping("/{id}")
    ResponseEntity<Response> delete(@PathVariable("id") String id);

    @GetMapping("/{id}")
    ResponseEntity<Response> get(@PathVariable("id") String id);

    @GetMapping()
    ResponseEntity<Response> getAll(Pageable pageable);

    @PostMapping("/filter")
    ResponseEntity<Response> filter(Pageable pageable, @RequestBody() DirectorDTO directorDTO);
}
