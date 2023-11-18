package com.harun.movie.mapper;

import com.harun.movie.dto.MovieDTO;
import com.harun.movie.model.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface MapperGenerator {

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<MovieDTO> movieToMovieDTO(List<Movie> movie);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie updateActorFromDTO(MovieDTO movieDTO, @MappingTarget() Movie movie);
}