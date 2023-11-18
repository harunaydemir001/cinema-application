package com.harun.movie.service;

import com.harun.common.interfaces.CrudOperationsService;
import com.harun.movie.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService extends CrudOperationsService<MovieDTO> {

    Page<MovieDTO> filter(Pageable pageable, MovieDTO movieDTO);

    Page<MovieDTO> getAll(Pageable pageable);
}
