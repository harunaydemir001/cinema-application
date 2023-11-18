package com.harun.actor.service;

import com.harun.actor.dto.ActorDTO;
import com.harun.common.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorService extends BaseService<String, ActorDTO> {

    Page<ActorDTO> filter(Pageable pageable, ActorDTO actorDTO);

    Page<ActorDTO> getAll(Pageable pageable);
}
