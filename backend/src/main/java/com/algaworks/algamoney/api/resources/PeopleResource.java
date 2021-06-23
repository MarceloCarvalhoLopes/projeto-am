package com.algaworks.algamoney.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.repositories.PeopleRepository;
import com.algaworks.algamoney.api.services.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleResource {

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private PeopleService peopleService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
	public Page<People> findByName(@RequestParam(required = false, defaultValue = "%") String name, Pageable pageable) {
		return peopleRepository.findByName(name, pageable);
	}
	
//	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
//	public List<People> findAll() {
//		return peopleRepository.findAll();
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
	public ResponseEntity<People> findById(@PathVariable Long id) {
		return peopleRepository.findById(id).map(people -> ResponseEntity.ok(people))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PEOPLE') and #oauth2.hasScope('write')")
	public ResponseEntity<People> create(@Valid @RequestBody People people, HttpServletResponse response) {
		People savedPeople = peopleRepository.save(people);

		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, savedPeople.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPeople);
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_PEOPLE') and #oauth2.hasScope('write')")
	public void remove(@PathVariable Long id) {
		this.peopleRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PEOPLE') and #oauth2.hasScope('write')")
	public ResponseEntity<People> update(@PathVariable Long id, @Valid @RequestBody People people){
		People 	savedPeople = peopleService.update(id, people);
		return ResponseEntity.ok(savedPeople);
	}
	
	@PutMapping("/{id}/active")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PEOPLE') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyActive(@PathVariable Long id, @RequestBody Boolean active) {
		peopleService.updatePropertyActive(id,active);
	}
	

}
