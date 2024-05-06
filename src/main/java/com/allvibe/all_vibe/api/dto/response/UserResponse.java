package com.allvibe.all_vibe.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;
    private List<SimpleEventParticipationResponseToUser> eventParticipation;

}
