package com.harun.actor.controller;

import com.harun.actor.service.IActorService;
import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
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


@Tag(
        name = "CRUD REST APIs for Actor in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER actor details")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/actor")
@Validated
public class ActorController implements BaseController<ActorDTO, String> {

    private final IActorService iActorService;

    @Override
    @Operation(summary = "Save Actor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Actor Created", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @PostMapping
    public ResponseEntity<Response> save(@Parameter(description = "Actor DTO", required = true) @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.save(actorDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update Actor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Parameter(description = "Actor Id", required = true) @PathVariable("id") String id,
                                           @Parameter(description = "Actor DTO", required = true) @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.update(actorDTO, id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Delete Actor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@Parameter(description = "Actor Id", required = true) @PathVariable("id") String id) {
        iActorService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    @Operation(summary = "Get Actor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@Parameter(description = "Actor Id", required = true) @PathVariable("id") String id) {
        return ResponseFactory.createResponse(iActorService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All Actors")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })

    @GetMapping()
    public ResponseEntity<Response> getAll(@ParameterObject Pageable pageable) {
        return ResponseFactory.createResponse(iActorService.getAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Filter Actors", description = "Filtered Actor By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(@ParameterObject Pageable pageable,
                                           @Parameter(description = "Actor DTO") @RequestBody() ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.filter(pageable, actorDTO), HttpStatus.OK);
    }

    @Operation(summary = "Get All Actors By Movie")
    @GetMapping("/by-movie")
    public ResponseEntity<Response> getAllByMovie(@Parameter(description = "Movie Title") @RequestParam String movieTitle) {
        return ResponseFactory.createResponse(iActorService.getAllByMovie(movieTitle), HttpStatus.OK);
    }
}
