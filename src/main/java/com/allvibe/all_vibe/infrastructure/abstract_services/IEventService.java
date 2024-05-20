package com.allvibe.all_vibe.infrastructure.abstract_services;

import java.util.List;

import com.allvibe.all_vibe.api.dto.request.EventRequest;
import com.allvibe.all_vibe.api.dto.response.EventResponse;
import com.allvibe.all_vibe.util.enums.TypeEvent;

public interface IEventService extends CrudService<EventRequest, EventResponse, Long> {
  public final String FIELD_BY_SORT = "name";
  
  public List<EventResponse> findByEventType(TypeEvent eventType);
}
