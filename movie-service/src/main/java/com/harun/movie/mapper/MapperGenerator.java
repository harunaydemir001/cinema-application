package com.harun.movie.mapper;

import com.harun.movie.dto.MovieDTO;
import com.harun.movie.model.Movie;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper
public interface MapperGenerator {

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<MovieDTO> movieToMovieDTO(List<Movie> movie);
}