package com.harun.actor.mapper;

import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.actor.model.Actor;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface MapperGenerator {

    Actor actorDTOToActor(ActorDTO actorDTO);

    ActorDTO actorToActorDTO(Actor actor);

    List<ActorDTO> actorToActorDTO(List<Actor> actor);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor updateActorFromDTO(ActorDTO actorDTO, @MappingTarget Actor actor);
}