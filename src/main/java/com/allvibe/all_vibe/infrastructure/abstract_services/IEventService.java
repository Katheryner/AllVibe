package com.allvibe.all_vibe.infrastructure.abstract_services;

import com.allvibe.all_vibe.api.dto.request.EventRequest;
import com.allvibe.all_vibe.api.dto.response.EventResponse;

public interface IEventService extends CrudService<EventRequest,EventResponse,Long>{

}
