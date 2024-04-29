package com.allvibe.all_vibe.entities;

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
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
  private Bolean isAdmin;

  @OneToMany(mappedBy = "festival", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
  @OneToMany(mappedBy = "conference", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
}