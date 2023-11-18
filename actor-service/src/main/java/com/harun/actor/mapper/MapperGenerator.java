package com.harun.actor.mapper;

import com.harun.actor.dto.ActorDTO;
import com.harun.actor.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface MapperGenerator {

    Actor actorDTOToActor(ActorDTO actorDTO);

    ActorDTO actorToActorDTO(Actor actor);

    List<ActorDTO> actorToActorDTO(List<Actor> actor);

    @Mapping(target = "id", ignore = true)
    void updateActorFromDTO(ActorDTO actorDTO, @MappingTarget Actor actor);
}