package com.allvibe.all_vibe.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allvibe.all_vibe.api.dto.request.EventRequest;
import com.allvibe.all_vibe.api.dto.response.EventResponse;
import com.allvibe.all_vibe.infrastructure.abstract_services.IEventService;
import com.allvibe.all_vibe.util.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {
  @Autowired
  private final IEventService service;

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

  @PostMapping
  public ResponseEntity<EventResponse> create(@Validated @RequestBody EventRequest request) {
    return ResponseEntity.ok(this.service.create(request));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<EventResponse> getById(@RequestParam Long id) {
    return ResponseEntity.ok(this.service.findByIdWithDetails(id));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<EventResponse> update(@Validated @RequestBody EventRequest request, @RequestParam Long id) {
    return ResponseEntity.ok(this.service.update(request, id));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@RequestParam Long id) {
    this.service.delete(id);
    return ResponseEntity.ok().build();
  }
}
