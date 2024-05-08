package com.allvibe.all_vibe.api.dto.response;

import com.allvibe.all_vibe.util.enums.RoleParticipation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEventParticipationResponseToEvent {

    private Long id;
    private RoleParticipation participantRole;
    private SimpleUserResponse simpleUserResponse;
}
