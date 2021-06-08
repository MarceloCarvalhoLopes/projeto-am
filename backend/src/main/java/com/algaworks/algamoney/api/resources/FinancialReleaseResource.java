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
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;

@RestController
@RequestMapping("/financial")
public class FinancialReleaseResource {

	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping
	public List<FinancialRelease> findAll(){
		return this.financialReleaseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FinancialRelease> findById(@PathVariable Long id){
		return this.financialReleaseRepository.findById(id)
				.map(financial -> ResponseEntity.ok(financial))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public ResponseEntity<FinancialRelease> create (@Valid @RequestBody FinancialRelease financialRelease, HttpServletResponse response ){
		FinancialRelease savedFinancial = this.financialReleaseRepository.save(financialRelease);
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, savedFinancial.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFinancial);
		
	}
	
}
