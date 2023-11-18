package com.harun.actor.repository;

import com.harun.actor.model.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActorRepository extends MongoRepository<Actor, String> {
}

