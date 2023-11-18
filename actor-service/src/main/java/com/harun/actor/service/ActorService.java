package com.harun.actor.service;

import com.harun.actor.dto.ActorDTO;
import com.harun.common.interfaces.CrudOperationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorService extends CrudOperationsService<ActorDTO> {

    Page<ActorDTO> filter(Pageable pageable, ActorDTO actorDTO);

    Page<ActorDTO> getAll(Pageable pageable);
}
