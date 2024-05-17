package com.allvibe.all_vibe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allvibe.all_vibe.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

}
