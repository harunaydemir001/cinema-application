package com.harun.movie.controller;

import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.movie.dto.MovieDTO;
import com.harun.movie.service.MovieService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(
        tags = "CRUD REST APIs for Movie in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER movie details")
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController implements BaseController<MovieDTO, Long> {
    private final MovieService movieService;

    @Override
    @ApiOperation(value = "Save Movie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Movie Created", response = Response.class)
    })
    @PostMapping
    public ResponseEntity<Response> save(@ApiParam(value = "Movie DTO", required = true) @Valid @RequestBody MovieDTO movieDTO) {
        return ResponseFactory.createResponse(movieService.save(movieDTO), HttpStatus.CREATED);
    }

    @Override
    @ApiOperation(value = "Update Movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @PutMapping
    public ResponseEntity<Response> update(@ApiParam(value = "Movie DTO", required = true) @Valid @RequestBody MovieDTO movieDTO) {
        return ResponseFactory.createResponse(movieService.update(movieDTO), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Delete Movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@ApiParam(value = "Movie Id", required = true) @PathVariable("id") Long id) {
        movieService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    @ApiOperation(value = "Get Movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@ApiParam(value = "Movie Id", required = true) @PathVariable("id") Long id) {
        return ResponseFactory.createResponse(movieService.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Movies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(movieService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Filter Movies", notes = "Filtered Movie By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(Pageable pageable,
                                           @ApiParam(value = "Movie DTO") @RequestBody() MovieDTO movieDTO) {
        return ResponseFactory.createResponse(movieService.filter(pageable, movieDTO), HttpStatus.OK);
    }

}
