package com.harun.actor.repository;

import com.harun.actor.dto.ActorDTO;
import com.harun.actor.model.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ActorDALImpl implements ActorDAL {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Actor> findByFilter(Pageable pageable, ActorDTO actorDTO) {
        Query query = new Query();

        if (!ObjectUtils.isEmpty(actorDTO.getId())) {
            query.addCriteria(Criteria.where("id").is(actorDTO.getId()));
        }

        if (!ObjectUtils.isEmpty(actorDTO.getName())) {
            query.addCriteria(Criteria.where("name").is(actorDTO.getName()));
        }

        if (!ObjectUtils.isEmpty(actorDTO.getSurname())) {
            query.addCriteria(Criteria.where("surname").is(actorDTO.getSurname()));
        }

        if (!ObjectUtils.isEmpty(actorDTO.getStatus())) {
            query.addCriteria(Criteria.where("status").is(actorDTO.getStatus()).ne("DELETED"));
        }

        query.skip((long) pageable.getPageNumber() * pageable.getPageSize());
        query.limit(pageable.getPageSize());

        return mongoTemplate.find(query, Actor.class);
    }
}
