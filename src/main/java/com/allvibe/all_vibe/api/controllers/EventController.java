package com.allvibe.all_vibe.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allvibe.all_vibe.api.dto.request.EventRequest;
import com.allvibe.all_vibe.api.dto.response.EventResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorsResponse;
import com.allvibe.all_vibe.infrastructure.abstract_services.IEventService;
import com.allvibe.all_vibe.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {
  @Autowired
  private final IEventService service;

  @Operation(summary = "Retrieve all existing events")
  @GetMapping
  public ResponseEntity<Page<EventResponse>> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestHeader(required = false) SortType sortType) {
    if (Objects.isNull(sortType)) {
      sortType = SortType.NONE;
    }
    return ResponseEntity.ok(this.service.findAll(page - 1, size, sortType));
  }

  @Operation(summary = "Create an event")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
  })
  @PostMapping
  public ResponseEntity<EventResponse> create(@Validated @RequestBody EventRequest request) {
    return ResponseEntity.ok(this.service.create(request));
  }

  @Operation(summary = "Find an event by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @GetMapping(path = "/{id}")
  public ResponseEntity<EventResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(this.service.findByIdWithDetails(id));
  }

  @Operation(summary = "Update an event by its ID number")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
  })
  @PutMapping(path = "/{id}")
  public ResponseEntity<EventResponse> update(@Validated @RequestBody EventRequest request, @PathVariable Long id) {
    return ResponseEntity.ok(this.service.update(request, id));
  }

  @Operation(summary = "Delete an event by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.service.delete(id);
    return ResponseEntity.ok().build();
  }
}
