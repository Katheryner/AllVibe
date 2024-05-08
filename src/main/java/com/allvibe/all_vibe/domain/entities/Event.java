package com.allvibe.all_vibe.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.allvibe.all_vibe.util.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String name;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private int capacity;
    @Column(length = 40, nullable = false)
    private String place;
    private String description;
    @Column(length = 40, nullable = false)
    private String eventType;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EventParticipation> eventParticipation;

}
