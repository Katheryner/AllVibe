package com.allvibe.all_vibe.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.allvibe.all_vibe.api.dto.request.EventParticipationRequest;
import com.allvibe.all_vibe.api.dto.response.EventParticipationResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorsResponse;
import com.allvibe.all_vibe.infrastructure.abstract_services.IEventParticipationService;
import com.allvibe.all_vibe.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/eventParticipation")
@AllArgsConstructor
public class EventParticipationController {
    private final IEventParticipationService eventService;

    @Operation(summary = "Retrieve all existing reservations")
    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<Page<EventParticipationResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.eventService.findAll(page - 1, size, sortType));
    }

    @Operation(summary = "Find a reservation by its ID number")
    @ApiResponse(responseCode = "404", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @GetMapping(path = "/{id}")
    @CrossOrigin("*")
    public ResponseEntity<EventParticipationResponse> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.findByIdWithDetails(id));
    }

    @Operation(summary = "Create a reservation")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
    })
    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<EventParticipationResponse> insert(
            @Validated @RequestBody EventParticipationRequest request) {
        return ResponseEntity.ok(this.eventService.create(request));
    }

    @Operation(summary = "Update a reservation")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
    })
    @PutMapping(path = "/{id}")
    @CrossOrigin("*")
    public ResponseEntity<EventParticipationResponse> update(
            @Validated @RequestBody EventParticipationRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.update(request, id));
    }

    @Operation(summary = "Delete a reservation")
    @ApiResponse(responseCode = "404", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @DeleteMapping(path = "/{id}")
    @CrossOrigin("*")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
