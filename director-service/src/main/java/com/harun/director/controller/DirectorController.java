package com.harun.director.controller;

import com.harun.common.base.BaseController;
import com.harun.common.factory.ResponseFactory;
import com.harun.common.model.Response;
import com.harun.director.service.IDirectorService;
import com.harun.directorserviceapi.dto.DirectorDTO;
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
        name = "CRUD REST APIs for Director in Cinema",
        description = "CRUD REST APIs in Cinema to CREATE, UPDATE, GET, DELETE And FILTER director details")
@RequiredArgsConstructor
@RequestMapping("/api/v1/director")
@Validated
public class DirectorController implements BaseController<DirectorDTO, Long> {

    private final IDirectorService iDirectorService;

    @Override
    @Operation(summary = "Save Director")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Director Created", content = {
                    @Content(schema =
                    @Schema(implementation = Response.class))
            })
    })
    @PostMapping
    public ResponseEntity<Response> save(@Parameter(description = "Director DTO", required = true) @RequestBody DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(iDirectorService.save(directorDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update Director")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Parameter(description = "Director Id", required = true) @PathVariable("id") Long id,
                                           @Parameter(description = "Director DTO", required = true) @RequestBody DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(iDirectorService.update(directorDTO, id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get Director")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@Parameter(description = "Director Id", required = true) @PathVariable(value = "id") Long id) {
        return ResponseFactory.createResponse(iDirectorService.get(id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Delete Director")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@Parameter(description = "Director Id", required = true) @PathVariable(value = "id") Long id) {
        iDirectorService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Operation(summary = "Get All Directors")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @GetMapping()
    public ResponseEntity<Response> getAll(@ParameterObject Pageable pageable) {
        return ResponseFactory.createResponse(iDirectorService.getAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Filter Directors", description = "Filtered Actor By Selection Field and Return Page")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(@ParameterObject Pageable pageable,
                                           @Parameter(description = "Director DTO") @RequestBody() DirectorDTO directorDTO) {
        return ResponseFactory.createResponse(iDirectorService.filter(pageable, directorDTO), HttpStatus.OK);
    }
}
