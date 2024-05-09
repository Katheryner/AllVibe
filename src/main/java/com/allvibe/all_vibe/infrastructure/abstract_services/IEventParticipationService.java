package com.allvibe.all_vibe.infrastructure.abstract_services;

import com.allvibe.all_vibe.api.dto.request.EventParticipationRequest;
import com.allvibe.all_vibe.api.dto.response.EventParticipationResponse;

public interface IEventParticipationService extends CrudService<EventParticipationRequest,EventParticipationResponse,Long>{

    public final String FIELD_BY_SORT = "participantRole";
}
