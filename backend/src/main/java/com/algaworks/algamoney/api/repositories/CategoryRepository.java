package com.algaworks.algamoney.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	

}
