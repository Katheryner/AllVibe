package com.allvibe.all_vibe.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(length = 40, nullable = false)
  private String name;
  @Column(length = 40, nullable = false)
  private String musicBand;
  @Column(length = 40, nullable = false)
  private String musicalGender;

  @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User user;
}
