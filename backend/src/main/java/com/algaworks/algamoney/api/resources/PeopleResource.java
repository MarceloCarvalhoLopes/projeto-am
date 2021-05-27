package com.algaworks.algamoney.api.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.repositories.PeopleRepository;

@RestController
@RequestMapping("/people")
public class PeopleResource {

	@Autowired
	private PeopleRepository peopleRepository;

	@GetMapping
	public List<People> findAll() {
		return peopleRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<People> create(@Valid @RequestBody People people, HttpServletResponse response) {
		People savedPeople = peopleRepository.save(people);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment("{/id}")
				.buildAndExpand(savedPeople.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(savedPeople);
	}

	@GetMapping("/{id}")
	public ResponseEntity<People> findById(@PathVariable Long id) {
		return peopleRepository.findById(id)
				.map(people -> ResponseEntity.ok(people))
				.orElse(ResponseEntity.notFound().build());
	}
}
