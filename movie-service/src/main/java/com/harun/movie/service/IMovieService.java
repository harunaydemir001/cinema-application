package com.harun.movie.service;

import com.harun.common.base.BaseService;
import com.harun.movie.dto.MovieAndActorDTO;
import com.harun.movie.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovieService extends BaseService<MovieDTO, Long> {

    Page<MovieDTO> filter(Pageable pageable, MovieDTO movieDTO);

    Page<MovieDTO> getAll(Pageable pageable);

    MovieAndActorDTO combineFilmAndActors(String title);
}
