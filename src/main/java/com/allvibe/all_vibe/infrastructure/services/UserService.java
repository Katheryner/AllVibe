package com.allvibe.all_vibe.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.allvibe.all_vibe.api.dto.request.UserRequest;
import com.allvibe.all_vibe.api.dto.response.UserResponse;
import com.allvibe.all_vibe.domain.repositories.EventParticipationRepository;
import com.allvibe.all_vibe.domain.repositories.UserRepository;
import com.allvibe.all_vibe.infrastructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final EventParticipationRepository eventParticipationRepository;

    // @Override
    // public Page<UserResponse> findAll(int page, int size) {
    // if (page < 0)
    // page = 0;
    // Pageable pageable = PageRequest.of(page, size);
    // return userRepository.findAll(pageable).map(this::UserToUserResponse);
    // }

    @Override
    public UserResponse findByIdWithDetails(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdWithDetails'");
    }

    @Override
    public UserResponse create(UserRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<UserResponse> findAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    // private UserResponse UserToUserResponse(User user) {
    // UserResponse userResponse = new UserResponse();
    // List<SimpleEventParticipationResponseToUser>
    // simpleEventParticipationResponseToUser = new ArrayList<>();
    // List<EventParticipation> events = user.getEventParticipation().stream()
    // .map(eventParticipation ->
    // eventToSimpleEventResponse(eventParticipation.getEvent()))
    // .collect(Collectors.toList());
    // BeanUtils.copyProperties(user.getEventParticipation(),
    // simpleEventParticipationResponseToUser);
    // userResponse.setEventParticipation(simpleEventParticipationResponseToUser);
    // BeanUtils.copyProperties(user, userResponse);
    // return userResponse;
    // }

    // private SimpleEventParticipationResponseToUser
    // eventParticipationToSimpleEventParticipationResponseToUser(
    // EventParticipation eventParticipation) {

    // }

    // private SimpleEventResponse eventToSimpleEventResponse(Event event) {
    // SimpleEventResponse simpleEventResponse = new SimpleEventResponse();
    // BeanUtils.copyProperties(event, simpleEventResponse);
    // return simpleEventResponse;
    // }

}
