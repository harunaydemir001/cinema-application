package com.harun.movie.service;

import com.harun.common.enums.StatusEnum;
import com.harun.common.interfaces.CrudOperationsService;
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
public class MovieServiceImpl implements MovieService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private final MovieRepository movieRepository;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        movieRepository.save(mapper.movieDTOToMovie(movieDTO));
        return movieDTO;
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
//        Movie incomingMovie = getMovieById(movieDTO.getId());
//
//        Movie updateMovie = Movie.builder()
//                .withId(incomingMovie.getId())
//                .withDescription(movieDTO.getDescription())
//                .withRating(movieDTO.getRating())
//                .withLength(movieDTO.getLength())
//                .withLanguageId(movieDTO.getLanguageId())
//                .withLastUpdate(movieDTO.getLastUpdate())
//                .withReleaseDate(movieDTO.getReleaseDate())
//                .withTitle(movieDTO.getTitle())
//                .withRentalDuration(movieDTO.getRentalDuration())
//                .withRentalRate(movieDTO.getRentalRate())
//                .withSpecialFeatures(movieDTO.getSpecialFeatures())
//                .withReplacementCost(movieDTO.getReplacementCost())
//                .withStatus(movieDTO.getStatus())
//                .build();
//        return mapper.movieToMovieDTO(movieRepository.save(updateMovie));
        return null;
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

        Page<Movie> productPage = movieRepository.findAll(pageable);
        List<MovieDTO> productDTOList = mapper.movieToMovieDTO(productPage.getContent());
        return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
    }

    private Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Movie not exist with id: " + id));
    }

}
