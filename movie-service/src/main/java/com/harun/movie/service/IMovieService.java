package com.harun.movie.service;

import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.base.BaseService;
import com.harun.movieserviceapi.dto.MovieAndActorDTO;
import com.harun.movieserviceapi.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Access Layer for {@link MovieService}
 */

public interface IMovieService extends BaseService<MovieDTO, Long> {

    Page<MovieDTO> filter(Pageable pageable, MovieDTO movieDTO);

    Page<MovieDTO> getAll(Pageable pageable);

    MovieAndActorDTO combineFilmAndActors(String title);

    Map<String, Page> getAllData();

    ActorDTO getActorById(String actorId);
}
