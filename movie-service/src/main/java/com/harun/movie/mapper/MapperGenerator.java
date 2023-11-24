package com.harun.movie.mapper;

import com.harun.movieserviceapi.dto.MovieDTO;
import com.harun.movie.model.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MapperGenerator {

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<MovieDTO> movieToMovieDTO(List<Movie> movie);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie updateMovieFromDTO(MovieDTO movieDTO, @MappingTarget() Movie movie);
}