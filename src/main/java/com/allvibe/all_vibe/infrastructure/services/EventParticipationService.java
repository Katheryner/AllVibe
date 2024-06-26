package com.allvibe.all_vibe.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.allvibe.all_vibe.api.dto.request.EventParticipationRequest;
import com.allvibe.all_vibe.api.dto.response.EventParticipationResponse;
import com.allvibe.all_vibe.api.dto.response.SimpleEventResponse;
import com.allvibe.all_vibe.api.dto.response.SimpleUserResponse;
import com.allvibe.all_vibe.domain.entities.Event;
import com.allvibe.all_vibe.domain.entities.EventParticipation;
import com.allvibe.all_vibe.domain.entities.User;
import com.allvibe.all_vibe.domain.repositories.EventParticipationRepository;
import com.allvibe.all_vibe.domain.repositories.EventRepository;
import com.allvibe.all_vibe.domain.repositories.UserRepository;
import com.allvibe.all_vibe.infrastructure.abstract_services.IEventParticipationService;
import com.allvibe.all_vibe.util.enums.SortType;
import com.allvibe.all_vibe.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventParticipationService implements IEventParticipationService {

    @Autowired
    private final EventParticipationRepository eventParticipationRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EventRepository eventRepository;

    @Override
    public Page<EventParticipationResponse> findAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            default -> throw new IllegalArgumentException("No valid sort: " + sortType);
        }

        Pageable pageable = pageRequest;
        return eventParticipationRepository.findAll(pageable).map(this::eventPartToResp);
    }

    @Override
    public EventParticipationResponse findByIdWithDetails(Long id) {
        EventParticipation eventParticipation = findByid(id);
        return eventPartToResp(eventParticipation);
    }

    @Override
    public EventParticipationResponse create(EventParticipationRequest request) {
        return eventPartToResp(eventParticipationRepository.save(requestToEntity(request, new EventParticipation())));
    }

    @Override
    public EventParticipationResponse update(EventParticipationRequest request, Long id) {
        EventParticipation eventPartToUpdate = findByid(id);
        return eventPartToResp(eventParticipationRepository.save(requestToEntity(request, eventPartToUpdate)));
    }

    @Override
    public void delete(Long id) {
        EventParticipation eventParticipation = findByid(id);
        eventParticipationRepository.delete(eventParticipation);
    }

    private EventParticipation findByid(Long id) {
        return eventParticipationRepository.findById(id).orElseThrow(
                () -> new BadRequestException("No found with the supplied id."));
    }

    private EventParticipationResponse eventPartToResp(EventParticipation eventParticipation) {
        EventParticipationResponse eventParticipationResponse = new EventParticipationResponse();
        User user = eventParticipation.getUser();
        Event event = eventParticipation.getEvent();
        eventParticipationResponse.setSimpleUserResponse(userToSimpleResponse(user));
        eventParticipationResponse.setSimpleEventResponse(eventToSimpleEventResponse(event));
        BeanUtils.copyProperties(eventParticipation, eventParticipationResponse);
        return eventParticipationResponse;
    }

    private SimpleUserResponse userToSimpleResponse(User user) {
        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
        BeanUtils.copyProperties(user, simpleUserResponse);
        return simpleUserResponse;
    }

    private SimpleEventResponse eventToSimpleEventResponse(Event event) {
        SimpleEventResponse simpleEventResponse = new SimpleEventResponse();
        BeanUtils.copyProperties(event, simpleEventResponse);
        return simpleEventResponse;
    }

    private EventParticipation requestToEntity(EventParticipationRequest request,
            EventParticipation eventParticipation) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        eventParticipation.setUser(user);
        eventParticipation.setEvent(event);
        BeanUtils.copyProperties(request, eventParticipation);
        return eventParticipation;
    }
}
