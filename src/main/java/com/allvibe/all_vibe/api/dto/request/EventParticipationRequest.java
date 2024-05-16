package com.allvibe.all_vibe.api.dto.request;

import com.allvibe.all_vibe.util.enums.RoleParticipation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipationRequest {
    @NotNull(message = "Roll is required.")
    private RoleParticipation participantRole;
    @NotNull(message = "User id is required.")
    private Long userId;
    @NotNull(message = "Event id is required.")
    private Long eventId;

}
