package com.allvibe.all_vibe.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.allvibe.all_vibe.util.enums.SortType;
import com.allvibe.all_vibe.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(int page, int size, SortType sortType) {
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
        List<EventParticipation> events = user.getEventParticipation();
        List<SimpleEventParticipationResponseToUser> simpleEventParticipationResponseToUser = events.stream()
                .map(this::evtPartToSimpleRespToUser).toList();

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .isAdmin(user.isAdmin())
                .eventParticipation(simpleEventParticipationResponseToUser).build();

    }

    private SimpleEventParticipationResponseToUser evtPartToSimpleRespToUser(
            EventParticipation eventParticipation) {

        Event event = eventParticipation.getEvent();
        SimpleEventResponse simpleEventResponse = eventToSimpleEventResponse(event);

        return SimpleEventParticipationResponseToUser.builder().id(eventParticipation.getId())
                .participantRole(eventParticipation.getParticipantRole()).simpleEventResponse(simpleEventResponse)
                .build();
    }

    private SimpleEventResponse eventToSimpleEventResponse(Event event) {
        return SimpleEventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .status(event.getStatus())
                .place(event.getPlace())
                .capacity(event.getCapacity()).date(event.getDate())
                .description(event.getDescription()).eventType(event.getEventType()).build();
    }

    private User requestToUser(UserRequest request, User user) {
        user.setEventParticipation(new ArrayList<>());
        BeanUtils.copyProperties(request, user);
        return user;
    }

    private User findByid(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new BadRequestException("No users were found with the supplied id."));
    }

}
