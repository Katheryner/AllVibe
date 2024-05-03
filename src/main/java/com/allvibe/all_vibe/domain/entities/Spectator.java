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

@Entity(name = "spectator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spectator {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(length = 40, nullable = false)
  private String username;
  @Column(length = 40, nullable = false)
  private String mailAddress;
  @Column(length = 40, nullable = false)
  private String password;
  @Column(nullable = false)
  private Boolean isAdmin;

  @OneToMany(mappedBy = "spectator", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User user;

}
