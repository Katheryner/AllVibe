package com.allvibe.all_vibe.domain.entities;

import java.time.LocalDate;
import java.util.List;

import com.allvibe.all_vibe.util.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "festival")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Status status;
    private LocalDate date;
    private int capacity;
    private String place;
    private List<User> users;
    private List<Artist> artists;
    private String description;
    private Artist mainArtist;
}
