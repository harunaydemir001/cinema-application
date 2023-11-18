package com.harun.actor.repository;

import com.harun.actor.dto.ActorDTO;
import com.harun.actor.model.Actor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActorDAL {
    List<Actor> findByFilter(Pageable pageable, ActorDTO actorDTO);
}
