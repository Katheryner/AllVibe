package com.allvibe.all_vibe.domain.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Spectator spectator;
    private Lecturer lecturer;
    private Artist artist;

}
