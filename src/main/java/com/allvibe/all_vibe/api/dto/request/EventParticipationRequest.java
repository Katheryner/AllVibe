package com.allvibe.all_vibe.api.dto.request;

import com.allvibe.all_vibe.util.enums.RoleParticipation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipationRequest {

    private RoleParticipation participantRole;
    private Long userId;
    private Long eventId;

}
