package com.example.demo.repository;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AppUser;
@Primary
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	Optional<AppUser> findByEmail(String email);
	Optional<AppUser> findByUsername(String email);
	Boolean existsByEmail(String email);
}