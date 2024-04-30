package com.allvibe.all_vibe.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lecturer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(length = 40, nullable = false)
  private String name;
  @Column(length = 40, nullable = false)
  private String specialty;

  // @OneToMany(mappedBy = "conference", fetch = FetchType.EAGER, cascade =
  // CascadeType.ALL, orphanRemoval = false)
  private Conference conference;
}