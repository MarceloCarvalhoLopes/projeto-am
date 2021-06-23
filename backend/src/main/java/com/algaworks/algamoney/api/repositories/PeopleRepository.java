package com.algaworks.algamoney.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	
	public Page<People> findByName(String name, Pageable pageable);
}
