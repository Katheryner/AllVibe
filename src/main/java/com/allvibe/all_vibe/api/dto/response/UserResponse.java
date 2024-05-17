package com.allvibe.all_vibe.api.dto.response;

import java.util.List;

import com.allvibe.all_vibe.util.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String password;
    private RoleUser role;
    private List<SimpleEventParticipationResponseToUser> eventParticipation;

}
