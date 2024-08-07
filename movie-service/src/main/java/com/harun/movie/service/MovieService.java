package com.harun.movie.service;


import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.actorserviceapi.service.ActorServiceClientService;
import com.harun.common.base.BaseDTO;
import com.harun.common.enums.StatusEnum;
import com.harun.common.util.JsonUtil;
import com.harun.common.util.ResponseExceptionUtil;
import com.harun.directorserviceapi.dto.DirectorDTO;
import com.harun.directorserviceapi.service.DirectorServiceClientService;
import com.harun.movie.constant.MovieErrorCodeConstant;
import com.harun.movie.mapper.MapperGenerator;
import com.harun.movie.mapper.MapperGeneratorSingleton;
import com.harun.movie.mapper.PageMapper;
import com.harun.movie.model.Movie;
import com.harun.movie.repository.MovieRepository;
import com.harun.movieserviceapi.dto.MovieAndActorDTO;
import com.harun.movieserviceapi.dto.MovieDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private static final String ALL_ACTORS = "allActors";
    private static final String ALL_DIRECTORS = "allDirectors";
    private static final String ALL_MOVIES = "allMovies";
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final MovieRepository movieRepository;
    private final ActorServiceClientService actorServiceClientService;
    private final DirectorServiceClientService directorServiceClientService;
    private final ResponseExceptionUtil responseExceptionUtil;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        Movie movie = mapper.movieDTOToMovie(movieDTO);
        return mapper.movieToMovieDTO(movieRepository.save(movie));
    }

    @Override
    @CachePut(value = "movie", key = "#result.id")
    @TimeLimiter(name = "timeLimiterApi")
    public MovieDTO update(MovieDTO movieDTO, Long id) {
        Movie incomingMovie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Movie movie = mapper.updateMovieFromDTO(movieDTO, incomingMovie);
        return mapper.movieToMovieDTO(movieRepository.save(movie));
    }

    @Override
    @CacheEvict(value = "movie", key = "#id")
    public void delete(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        movie.setStatus(StatusEnum.DELETED);
        if (logger.isInfoEnabled())
            logger.info("Movie status change to " + StatusEnum.DELETED + " with id: " + id);
        movieRepository.save(movie);
    }

    @Override
    @Cacheable(value = "movie", key = "#id")
    @Retry(name = "retryApi")
    public MovieDTO get(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.movieToMovieDTO(movie);
    }

    @Override
    @RateLimiter(name = "rateLimiterApi")
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
    @CircuitBreaker(name = "circuitBreakerApi", fallbackMethod = "getAllDataFallback")
    public Map<String, Page<? extends BaseDTO<? extends Serializable>>> getAllData(Pageable pageable) {
        Page<ActorDTO> allActors = actorServiceClientService.getAll(pageable);
        Page<DirectorDTO> allDirectors = directorServiceClientService.getAll(pageable);
        Page<MovieDTO> allMovies = getAll(pageable);

        return Map.of(
                ALL_ACTORS, allActors,
                ALL_DIRECTORS, allDirectors,
                ALL_MOVIES, allMovies);
    }

    @Override
    public ActorDTO getActorById(String actorId) {
        ActorDTO actorDTO = actorServiceClientService.get(actorId);
        if (Objects.equals(actorDTO, new ActorDTO()))
            responseExceptionUtil.throwResponseException(HttpStatus.NOT_FOUND, MovieErrorCodeConstant.OBJECT_NOT_FOUND, "Actor");
        return actorDTO;
    }

    public Map<String, Page<? extends BaseDTO<? extends Serializable>>> getAllDataFallback(Pageable pageable, Exception e) {
        Page<ActorDTO> allActors = actorServiceClientService.getAll(pageable);
        Page<DirectorDTO> allDirectors = directorServiceClientService.getAll(pageable);
        Page<MovieDTO> allMovies = Page.empty();

        return Map.of(
                ALL_ACTORS, allActors,
                ALL_DIRECTORS, allDirectors,
                ALL_MOVIES, allMovies);
    }
}
