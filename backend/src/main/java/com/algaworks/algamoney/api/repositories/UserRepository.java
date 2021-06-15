package com.algaworks.algamoney.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.UserSystem;

public interface UserRepository extends JpaRepository<UserSystem, Long>{
	
	public Optional<UserSystem> findByEmail(String email);
}
