package com.harun.movie.service;


import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.actorserviceapi.service.ActorServiceClientService;
import com.harun.common.enums.StatusEnum;
import com.harun.common.util.JsonUtil;
import com.harun.directorserviceapi.dto.DirectorDTO;
import com.harun.directorserviceapi.service.DirectorServiceClientService;
import com.harun.movie.mapper.MapperGenerator;
import com.harun.movie.mapper.MapperGeneratorSingleton;
import com.harun.movie.mapper.PageMapper;
import com.harun.movie.model.Movie;
import com.harun.movie.repository.MovieRepository;
import com.harun.movieserviceapi.dto.MovieAndActorDTO;
import com.harun.movieserviceapi.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private final MovieRepository movieRepository;
    private final ActorServiceClientService actorServiceClientService;
    private final DirectorServiceClientService directorServiceClientService;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        Movie movie = mapper.movieDTOToMovie(movieDTO);
        movieRepository.save(movie);
        return get(movie.getId());
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO, Long id) {
        movieRepository.save(mapper.updateActorFromDTO(movieDTO, getMovieById(id)));
        return get(id);
    }

    @Override
    public void delete(Long id) {
        Movie movie = getMovieById(id);
        movie.setStatus(StatusEnum.DELETED);
        movieRepository.save(movie);
    }

    @Override
    public MovieDTO get(Long id) {
        Movie movie = getMovieById(id);
        return mapper.movieToMovieDTO(movie);
    }

    @Override
    public Page<MovieDTO> filter(Pageable pageable, MovieDTO movieDTO) {
        Page<Movie> page = movieRepository.findByFilter(pageable, movieDTO);
        List<MovieDTO> movieDTOList = mapper.movieToMovieDTO(page.getContent());
        return PageMapper.toPage(page, movieDTOList);
    }

    @Override
    public Page<MovieDTO> getAll(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<MovieDTO> movieDTOList = mapper.movieToMovieDTO(moviePage.getContent());
        return new PageImpl<>(movieDTOList, pageable, moviePage.getTotalElements());
    }

    @Override
    public MovieAndActorDTO combineFilmAndActors(String title) {
        List<ActorDTO> actorDTOList = actorServiceClientService.getAllByMovie(title);
        MovieDTO dto = JsonUtil.convertValue(filter(
                Pageable.unpaged(),
                MovieDTO.builder().withTitle(title).build()).getContent().get(0), MovieDTO.class);
        return MovieAndActorDTO.builder()
                .withActorDTOList(actorDTOList)
                .withMovieDTO(dto)
                .build();
    }

    @Override
    public Map<String, Page> getAllData() {

        Page<ActorDTO> allActors = actorServiceClientService.getAll(Pageable.unpaged());
        Page<DirectorDTO> allDirectors = directorServiceClientService.getAll(Pageable.unpaged());
        Page<MovieDTO> allMovies = getAll(Pageable.unpaged());

        return Map.of(
                "allActors", allActors,
                "allDirectors", allDirectors,
                "allMovies", allMovies);
    }

    private Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Movie not exist with id: " + id));
    }
}
