package com.harun.director.controller;

import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.director.dto.DirectorDTO;
import com.harun.director.service.DirectorService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(
        tags = "CRUD REST APIs for Director in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER director details")
@RequiredArgsConstructor
@RequestMapping("/director")
public class DirectorController implements BaseController<DirectorDTO, Long> {

    private final DirectorService directorService;

    @Override
    @ApiOperation(value = "Save Director")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Director Created", response = Response.class)
    })
    @PostMapping
    public ResponseEntity<Response> save(@ApiParam(value = "Director DTO", required = true) @Valid @RequestBody DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(directorService.save(directorDTO), HttpStatus.CREATED);
    }

    @Override
    @ApiOperation(value = "Update Director")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@ApiParam(value = "Director Id", required = true) @PathVariable("id") Long id,
                                           @ApiParam(value = "Director DTO", required = true) @Valid @RequestBody DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(directorService.update(directorDTO, id), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Get Director")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@ApiParam(value = "Director Id", required = true) @PathVariable(value = "id") Long id) {
        return ResponseFactory.createResponse(directorService.get(id), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Delete Director")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@ApiParam(value = "Director Id", required = true) @PathVariable(value = "id") Long id) {
        directorService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @ApiOperation(value = "Get All Directors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HTTP Status OK", response = Response.class)
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseFactory.createResponse(directorService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Filter Directors", notes = "Filtered Actor By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(Pageable pageable,
                                           @ApiParam(value = "Director DTO") @RequestBody() DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(directorService.filter(pageable, directorDTO), HttpStatus.OK);
    }
}
