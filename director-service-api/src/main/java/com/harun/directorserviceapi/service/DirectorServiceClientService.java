package com.harun.directorserviceapi.service;


import com.harun.common.configuration.PageResponseConfig;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.common.util.JsonUtil;
import com.harun.directorserviceapi.client.DirectorServiceClient;
import com.harun.directorserviceapi.dto.DirectorDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service for {@link DirectorServiceClient}
 */
@Service
public class DirectorServiceClientService {
    private final DirectorServiceClient directorServiceClient;

    public DirectorServiceClientService(@Qualifier("") DirectorServiceClient directorServiceClient) {
        this.directorServiceClient = directorServiceClient;
    }

    public DirectorDTO save(DirectorDTO movieDTO) {
        ResponseEntity<Response> response = directorServiceClient.save(movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), DirectorDTO.class);
    }

    public DirectorDTO update(String id, DirectorDTO movieDTO) {
        ResponseEntity<Response> response = directorServiceClient.update(id, movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), DirectorDTO.class);
    }

    public ResponseEntity<Response> delete(String id) {
        directorServiceClient.delete(id);
        return ResponseFactory.createResponse(HttpStatus.OK);
    }

    public DirectorDTO get(String id) {
        ResponseEntity<Response> response = directorServiceClient.get(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), DirectorDTO.class);
    }

    public Page<DirectorDTO> getAll(Pageable pageable) {
        ResponseEntity<Response> response = directorServiceClient.getAll(pageable);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }

    public Page<DirectorDTO> filter(Pageable pageable, DirectorDTO movieDTO) {
        ResponseEntity<Response> response = directorServiceClient.filter(pageable, movieDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }
}
