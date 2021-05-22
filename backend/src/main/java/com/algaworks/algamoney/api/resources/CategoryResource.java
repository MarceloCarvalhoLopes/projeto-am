package com.algaworks.algamoney.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.models.Category;
import com.algaworks.algamoney.api.repositories.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
		Category categorySaved = categoryRepository.save(category);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment("{/id}")
				.buildAndExpand(categorySaved.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categorySaved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return this.categoryRepository.findById(id)
				.map(category -> ResponseEntity.ok(category))
				.orElse(ResponseEntity.notFound().build());
	}

}
