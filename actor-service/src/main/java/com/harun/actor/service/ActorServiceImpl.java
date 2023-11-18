package com.harun.actor.service;

import com.harun.actor.dto.ActorDTO;
import com.harun.actor.mapper.MapperGenerator;
import com.harun.actor.mapper.MapperGeneratorSingleton;
import com.harun.actor.model.Actor;
import com.harun.actor.repository.ActorDALImpl;
import com.harun.actor.repository.ActorRepository;
import com.harun.common.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ActorServiceImpl implements ActorService{
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final ActorRepository actorRepository;
    private final ActorDALImpl actorDALImpl;

    @Override
    public ActorDTO save(ActorDTO actorDTO) {
        actorRepository.save(mapper.actorDTOToActor(actorDTO));
        return actorDTO;
    }

    @Override
    public ActorDTO update(ActorDTO actorDTO) {
        return null;
    }

    @Override
    public void delete(String id) {
        Actor actor = getActorById(id);
        actor.setStatus(StatusEnum.DELETED);
        actorRepository.save(actor);
    }

    @Override
    public ActorDTO get(String id) {
        Actor actor = getActorById(id);
        return mapper.actorToActorDTO(actor);
    }

    @Override
    public Page<ActorDTO> filter(Pageable pageable, ActorDTO actorDTO) {
        List<Actor> listActor = actorDALImpl.findByFilter(pageable, actorDTO);
        List<ActorDTO> actorDTOList = mapper.actorToActorDTO(listActor);
        return new PageImpl<>(actorDTOList, pageable, listActor.size());
    }
    @Override
    public Page<ActorDTO> getAll(Pageable pageable) {
        Page<Actor> productPage = actorRepository.findAll(pageable);
        List<ActorDTO> productDTOList = mapper.actorToActorDTO(productPage.getContent());
        return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
    }

    private Actor getActorById(String id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Actor not exist with id: " + id));
    }
}
