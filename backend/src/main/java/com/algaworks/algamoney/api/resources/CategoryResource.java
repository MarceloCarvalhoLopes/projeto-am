package com.algaworks.algamoney.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.models.Category;
import com.algaworks.algamoney.api.repositories.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@GetMapping
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category savedcategory = categoryRepository.save(category);

		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, savedcategory.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(savedcategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return this.categoryRepository.findById(id).map(category -> ResponseEntity.ok(category))
				.orElse(ResponseEntity.notFound().build());
	}

}
