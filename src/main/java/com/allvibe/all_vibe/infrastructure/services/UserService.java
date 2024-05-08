package com.allvibe.all_vibe.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allvibe.all_vibe.api.dto.request.UserRequest;
import com.allvibe.all_vibe.api.dto.response.SimpleEventParticipationResponseToUser;
import com.allvibe.all_vibe.api.dto.response.SimpleEventResponse;
import com.allvibe.all_vibe.api.dto.response.UserResponse;
import com.allvibe.all_vibe.domain.entities.Event;
import com.allvibe.all_vibe.domain.entities.EventParticipation;
import com.allvibe.all_vibe.domain.entities.User;
import com.allvibe.all_vibe.domain.repositories.UserRepository;
import com.allvibe.all_vibe.infrastructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(int page, int size) {
        if (page < 0)
            page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(this::userToUserResponse);
    }

    @Override
    public UserResponse findByIdWithDetails(Long id) {
        return userToUserResponse(findByid(id));
    }

    @Override
    public UserResponse create(UserRequest request) {
        return userToUserResponse(userRepository.save(requestToUser(request, new User())));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = findByid(id);
        return userToUserResponse(userRepository.save(requestToUser(request, user)));
    }

    @Override
    public void delete(Long id) {
        User user = findByid(id);
        userRepository.delete(user);
    }

    private UserResponse userToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        List<EventParticipation> events = user.getEventParticipation();
        List<SimpleEventParticipationResponseToUser> simpleEventParticipationResponseToUser = events.stream()
                .map(this::evtPartToSimpleRespToUser).collect(Collectors.toList());
        userResponse.setEventParticipation(simpleEventParticipationResponseToUser);
        return userResponse;

    }

    private SimpleEventParticipationResponseToUser evtPartToSimpleRespToUser(
            EventParticipation eventParticipation) {
        SimpleEventParticipationResponseToUser simpEventResp = new SimpleEventParticipationResponseToUser();
        Event event = eventParticipation.getEvent();
        SimpleEventResponse simpleEventResponse = eventToSimpleEventResponse(event);
        simpEventResp.setSimpleEventResponse(simpleEventResponse);
        BeanUtils.copyProperties(eventParticipation, simpEventResp);

        return simpEventResp;
    }

    private SimpleEventResponse eventToSimpleEventResponse(Event event) {
        SimpleEventResponse simpleEventResponse = new SimpleEventResponse();
        BeanUtils.copyProperties(event, simpleEventResponse);
        return simpleEventResponse;
    }

    private User requestToUser(UserRequest request, User user) {
        user.setEventParticipation(new ArrayList<>());
        BeanUtils.copyProperties(request, user);
        return user;
    }

    private User findByid(Long id){
        return userRepository.findById(id).orElseThrow();
    }

}
