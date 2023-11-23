package com.harun.actor.service;

import com.harun.actor.mapper.MapperGenerator;
import com.harun.actor.mapper.MapperGeneratorSingleton;
import com.harun.actor.model.Actor;
import com.harun.actor.repository.ActorDALImpl;
import com.harun.actor.repository.ActorRepository;
import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ActorService implements IActorService {
    private static final Logger logger = LoggerFactory.getLogger(ActorService.class);
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final ActorRepository actorRepository;
    private final ActorDALImpl actorDALImpl;

    @Override
    public ActorDTO save(ActorDTO actorDTO) {
        Actor actor = mapper.actorDTOToActor(actorDTO);
        actorRepository.save(actor);
        logger("save", actor.getId());
        return get(actor.getId());
    }

    @Override
    public ActorDTO update(ActorDTO actorDTO, String id) {
        actorRepository.save(mapper.updateActorFromDTO(actorDTO, getActorById(id)));
        logger("update", id);
        return get(id);
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
        Page<Actor> actorPage = actorRepository.findAll(pageable);
        List<ActorDTO> actorDTOList = mapper.actorToActorDTO(actorPage.getContent());
        return new PageImpl<>(actorDTOList, pageable, actorPage.getTotalElements());
    }

    @Override
    public List<ActorDTO> getAllByMovie(String movieTitle) {
        return getAll(Pageable.unpaged()).getContent().stream()
                .filter(actor -> actor.getMovieTitles().contains(movieTitle))
                .collect(Collectors.toList());
    }

    private Actor getActorById(String id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Actor not exist with id: " + id));
    }
    private void logger(String action, String id) {
        if (logger.isInfoEnabled())
            logger.info("Actor " + action + " with id {}", id);
        else
            logger.debug("Actor " + action + " successfully with id {}", id);
    }
}
