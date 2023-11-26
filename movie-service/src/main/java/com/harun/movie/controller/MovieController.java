package com.harun.movie.controller;

import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.movieserviceapi.dto.MovieDTO;
import com.harun.movie.service.IMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(
        name = "CRUD REST APIs for Movie in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER movie details")
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
@Validated
public class MovieController implements BaseController<MovieDTO, Long> {
    private final IMovieService iMovieService;

    @Override
    @Operation(summary = "Save Movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie Created", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))
            })
    })
    @PostMapping
    public ResponseEntity<Response> save(@Parameter(description = "Movie DTO", required = true) @RequestBody MovieDTO movieDTO) {
        return ResponseFactory.createResponse(iMovieService.save(movieDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update Movie")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Parameter(description = "Movie Id", required = true) @PathVariable("id") Long id,
                                           @Parameter(description = "Movie DTO", required = true) @RequestBody MovieDTO movieDTO) {
        return ResponseFactory.createResponse(iMovieService.update(movieDTO, id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Delete Movie")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@Parameter(description = "Movie Id", required = true) @PathVariable("id") Long id) {
        iMovieService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    @Operation(summary = "Get Movie")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@Parameter(description = "Movie Id", required = true) @PathVariable("id") Long id) {
        return ResponseFactory.createResponse(iMovieService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All Movies")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))
            })
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(@ParameterObject Pageable pageable) {
        return ResponseFactory.createResponse(iMovieService.getAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Filter Movies", description = "Filtered Movie By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(@ParameterObject Pageable pageable,
                                           @Parameter(description = "Movie DTO") @RequestBody() MovieDTO movieDTO) {
        return ResponseFactory.createResponse(iMovieService.filter(pageable, movieDTO), HttpStatus.OK);
    }

    @Operation(summary = "Combine Film And Actors")
    @GetMapping("/combine-actors")
    public ResponseEntity<Response> combineFilmAndActors(@Parameter(description = "Movie Title") @RequestParam() String title) {
        return ResponseFactory.createResponse(iMovieService.combineFilmAndActors(title), HttpStatus.OK);
    }

    @Operation(summary = "Get All Movies, Actors and Directors")
    @GetMapping("/all-data")
    public ResponseEntity<Response> getAllData() {
        return ResponseFactory.createResponse(iMovieService.getAllData(), HttpStatus.OK);
    }


    @Operation(summary = "Get Actor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Response.class))
            })
    })
    @GetMapping("actor/{id}")
    public ResponseEntity<Response> getActorById(@Parameter(description = "Actor Id", required = true) @PathVariable("id") String id) {
        return ResponseFactory.createResponse(iMovieService.getActorById(id), HttpStatus.OK);
    }
}
