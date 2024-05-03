package com.allvibe.all_vibe.domain.entities;

import com.allvibe.all_vibe.util.enums.RoleParticipation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "event_participations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false)
    private RoleParticipation participantRole;
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

}
