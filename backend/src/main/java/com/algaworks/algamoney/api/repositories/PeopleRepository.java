package com.algaworks.algamoney.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	

}
