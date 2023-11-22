package com.harun.actorserviceapi.service;

import com.harun.actorserviceapi.client.ActorServiceClient;
import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.configuration.PageResponseConfig;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service for {@link ActorServiceClient}
 */
@Service
public class ActorServiceClientService {
    private final ActorServiceClient actorServiceClient;

    public ActorServiceClientService(@Qualifier("com.harun.actorserviceapi.client.ActorServiceClient") ActorServiceClient actorServiceClient) {
        this.actorServiceClient = actorServiceClient;
    }

    public ActorDTO save(ActorDTO actorDTO) {
        ResponseEntity<Response> response = actorServiceClient.save(actorDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), ActorDTO.class);
    }

    public ActorDTO update(String id, ActorDTO actorDTO) {
        ResponseEntity<Response> response = actorServiceClient.update(id, actorDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), ActorDTO.class);
    }

    public ResponseEntity<Response> delete(String id) {
        actorServiceClient.delete(id);
        return ResponseFactory.createResponse(HttpStatus.OK);
    }

    public ActorDTO get(String id) {
        ResponseEntity<Response> response = actorServiceClient.get(id);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), ActorDTO.class);
    }

    public Page<ActorDTO> getAll(Pageable pageable) {
        ResponseEntity<Response> response = actorServiceClient.getAll(pageable);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }

    public Page<ActorDTO> filter(Pageable pageable, ActorDTO actorDTO) {
        ResponseEntity<Response> response = actorServiceClient.filter(pageable, actorDTO);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), PageResponseConfig.class);
    }

    public List<ActorDTO> getAllByMovie(String movieTitle) {
        ResponseEntity<Response> response = actorServiceClient.getAllByMovie(movieTitle);
        return JsonUtil.convertValue(Objects.requireNonNull(response.getBody()).getResult(), List.class);
    }
}
