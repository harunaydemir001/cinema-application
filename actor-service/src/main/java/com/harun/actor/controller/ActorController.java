package com.harun.actor.controller;

import com.harun.actor.service.IActorService;
import com.harun.actorserviceapi.dto.ActorDTO;
import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(
        tags = "CRUD REST APIs for Actor in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER actor details")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/actor")
@Validated
public class ActorController implements BaseController<ActorDTO, String> {

    private final IActorService iActorService;

    @Override
    @ApiOperation(value = "Save Actor")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Actor Created", response = Response.class)
    })
    @PostMapping
    public ResponseEntity<Response> save(@ApiParam(value = "Actor DTO", required = true) @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.save(actorDTO), HttpStatus.CREATED);
    }

    @Override
    @ApiOperation(value = "Update Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@ApiParam(value = "Actor Id", required = true) @PathVariable("id") String id,
                                           @ApiParam(value = "Actor DTO", required = true) @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.update(actorDTO, id), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Delete Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@ApiParam(value = "Actor Id", required = true) @PathVariable("id") String id) {
        iActorService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    @ApiOperation(value = "Get Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@ApiParam(value = "Actor Id", required = true) @PathVariable("id") String id) {
        return ResponseFactory.createResponse(iActorService.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Actors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(iActorService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Filter Actors", notes = "Filtered Actor By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(Pageable pageable,
                                           @ApiParam(value = "Actor DTO") @RequestBody() ActorDTO actorDTO) {
        return ResponseFactory.createResponse(iActorService.filter(pageable, actorDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Actors By Movie")
    @GetMapping("/by-movie")
    public ResponseEntity<Response> getAllByMovie(@ApiParam(value = "Movie Title") @RequestParam String movieTitle) {
        return ResponseFactory.createResponse(iActorService.getAllByMovie(movieTitle), HttpStatus.OK);
    }
}
