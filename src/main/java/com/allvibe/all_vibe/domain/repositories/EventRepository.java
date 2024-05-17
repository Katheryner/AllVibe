package com.allvibe.all_vibe.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allvibe.all_vibe.domain.entities.Event;
import com.allvibe.all_vibe.util.enums.TypeEvent;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  public List<Event> findByEventType(TypeEvent eventType);

}
