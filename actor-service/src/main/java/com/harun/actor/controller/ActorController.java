package com.harun.actor.controller;

import com.harun.actor.dto.ActorDTO;
import com.harun.actor.service.ActorServiceImpl;
import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(
        tags = "CRUD REST APIs for Actor in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER actor details")
@RequiredArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController implements BaseController<String, ActorDTO> {

    private final ActorServiceImpl actorServiceImpl;

    @Override
    @ApiOperation(value = "Save Actor")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Actor Created", response = Response.class)
    })
    @PostMapping
    public ResponseEntity<Response> save(@ApiParam(value = "Actor DTO", required = true) @Valid @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(actorServiceImpl.save(actorDTO), HttpStatus.CREATED);
    }

    @Override
    @ApiOperation(value = "Update Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @PutMapping
    public ResponseEntity<Response> update(@ApiParam(value = "Actor DTO", required = true) @Valid @RequestBody ActorDTO actorDTO) {
        return ResponseFactory.createResponse(actorServiceImpl.update(actorDTO), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Delete Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@ApiParam(value = "Actor Id", required = true) @PathVariable("id") String id) {
        actorServiceImpl.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    @ApiOperation(value = "Get Actor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@ApiParam(value = "Actor Id", required = true) @PathVariable("id") String id) {
        return ResponseFactory.createResponse(actorServiceImpl.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Actors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(actorServiceImpl.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Filter Actor", notes = "Filtered Actor By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(Pageable pageable,
                                           @ApiParam(value = "Actor DTO") @RequestBody() ActorDTO actorDTO) {
        return ResponseFactory.createResponse(actorServiceImpl.filter(pageable, actorDTO), HttpStatus.OK);
    }
}
