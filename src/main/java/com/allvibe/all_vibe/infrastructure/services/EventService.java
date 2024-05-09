package com.allvibe.all_vibe.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allvibe.all_vibe.api.dto.request.EventRequest;
import com.allvibe.all_vibe.api.dto.response.EventResponse;
import com.allvibe.all_vibe.api.dto.response.SimpleEventParticipationResponseToEvent;
import com.allvibe.all_vibe.api.dto.response.SimpleUserResponse;
import com.allvibe.all_vibe.domain.entities.Event;
import com.allvibe.all_vibe.domain.entities.EventParticipation;
import com.allvibe.all_vibe.domain.repositories.EventRepository;
import com.allvibe.all_vibe.infrastructure.abstract_services.IEventService;
import com.allvibe.all_vibe.util.enums.SortType;
import com.allvibe.all_vibe.util.exceptions.IdNotFoundExeption;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EventService implements IEventService {

  @Autowired
  private final EventRepository eventRepository;

  @Override
  public Page<EventResponse> findAll(int page, int size, SortType sortType) {
    if (page < 0)
      page = 0;
    PageRequest pagination = null;

    switch (sortType) {
      case NONE -> pagination = PageRequest.of(page, size);
      case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
      case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    }
    return this.eventRepository.findAll(pagination).map(this::entityToResp);

  }

  @Override
  public EventResponse findByIdWithDetails(Long id) {
    Event response = this.find(id);
    return this.entityToResp(response);

  }

  @Override
  public EventResponse create(EventRequest request) {
    Event entity = this.eventRepository.save(this.requestToEntity(request, new Event()));
    entity.setEventParticipation(new ArrayList<>());
    return this.entityToResp(entity);
  }

  @Override
  public EventResponse update(EventRequest request, Long id) {
    Event entityUpdate = this.find(id);
    Event entity = this.requestToEntity(request, entityUpdate);
    return this.entityToResp(this.eventRepository.save(entity));
  }

  @Override
  public void delete(Long id) {
    Event entity = this.find(id);
    this.eventRepository.delete(entity);
  }

  private EventResponse entityToResp(Event event) {
    List<SimpleEventParticipationResponseToEvent> list = event.getEventParticipation().stream()
        .map(this::entityToResponseToEventResponse)
        .collect(Collectors.toList());

    return EventResponse.builder()
        .eventParticipation(list)
        .id(event.getId())
        .name(event.getName())
        .status(event.getStatus())
        .date(event.getDate())
        .capacity(event.getCapacity())
        .place(event.getPlace())
        .description(event.getDescription())
        .eventType(event.getEventType())
        .build();
  }

  private SimpleEventParticipationResponseToEvent entityToResponseToEventResponse(EventParticipation obj) {
    SimpleUserResponse response = new SimpleUserResponse();
    BeanUtils.copyProperties(obj.getUser(), response);
    return SimpleEventParticipationResponseToEvent.builder()
        .id(obj.getId())
        .participantRole(obj.getParticipantRole())
        .simpleUserResponse(response)
        .build();
  }

  private Event requestToEntity(EventRequest request, Event event) {
    BeanUtils.copyProperties(request, event);
    return event;
  }

  private Event find(Long id) {
    return this.eventRepository.findById(id).orElseThrow(() -> new IdNotFoundExeption("events"));
  }

}
