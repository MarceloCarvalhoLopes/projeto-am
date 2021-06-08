package com.algaworks.algamoney.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;

@RestController
@RequestMapping("/financial")
public class FinancialReleaseResource {

	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
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
}
