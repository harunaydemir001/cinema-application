package com.harun.movie.service;


import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.actorserviceapi.service.ActorServiceClientService;
import com.harun.common.enums.StatusEnum;
import com.harun.common.util.JsonUtil;
import com.harun.movie.dto.MovieAndActorDTO;
import com.harun.movie.dto.MovieDTO;
import com.harun.movie.mapper.MapperGenerator;
import com.harun.movie.mapper.MapperGeneratorSingleton;
import com.harun.movie.mapper.PageMapper;
import com.harun.movie.model.Movie;
import com.harun.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private final MovieRepository movieRepository;
    private final ActorServiceClientService actorServiceClientService;

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
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(title);
        List<ActorDTO> actorDTOList = actorServiceClientService.getAllByMovie(title);
        MovieDTO dto = JsonUtil.convertValue(filter(Pageable.unpaged(), movieDTO).getContent().get(0), MovieDTO.class);
        MovieAndActorDTO movieAndActorDTO = new MovieAndActorDTO();
        movieAndActorDTO.setActorDTOList(actorDTOList);
        movieAndActorDTO.setMovieDTO(dto);
        return movieAndActorDTO;
    }

    private Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Movie not exist with id: " + id));
    }
}
