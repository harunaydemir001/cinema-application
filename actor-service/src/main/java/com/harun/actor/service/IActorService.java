package com.harun.actor.service;


import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Access Layer for {@link ActorService}
 */


public interface IActorService extends BaseService<ActorDTO, String> {

    Page<ActorDTO> filter(Pageable pageable, ActorDTO actorDTO);

    Page<ActorDTO> getAll(Pageable pageable);

    List<ActorDTO> getAllByMovie(String movieTitle);
}
