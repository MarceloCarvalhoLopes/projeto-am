package com.algaworks.algamoney.api.resources;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.dto.FinancialStatisticCategory;
import com.algaworks.algamoney.api.dto.FinancialStatisticDay;
import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.exceptionhander.AlgamoneyExceptionHandler.Error;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;
import com.algaworks.algamoney.api.repositories.projections.FinancialReleaseResume;
import com.algaworks.algamoney.api.services.FinancialReleaseService;
import com.algaworks.algamoney.api.services.exception.PeopleNonExistentOrInactive;

@RestController
@RequestMapping("/financial")
public class FinancialReleaseResource {

	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private FinancialReleaseService financialReleaseService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/statistic/by-day")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_FINANCIAL_RELEASE') and #oauth2.hasScope('read')")
	public List<FinancialStatisticDay> byDay(){
		return this.financialReleaseRepository.byDay(LocalDate.now());
	}
	
	@GetMapping("/statistic/by-category")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_FINANCIAL_RELEASE') and #oauth2.hasScope('read')")
	public List<FinancialStatisticCategory> byCategory(){
		return this.financialReleaseRepository.byCategory(LocalDate.now());
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_FINANCIAL_RELEASE') and #oauth2.hasScope('read')")
	public Page<FinancialRelease> find(FinancialReleaseFilter financialReleaseFilter, Pageable pageable  ){
		return this.financialReleaseRepository.filter(financialReleaseFilter, pageable);
	}
	
	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_FINANCIAL_RELEASE') and #oauth2.hasScope('read')")
	public Page<FinancialReleaseResume> resume(FinancialReleaseFilter financialReleaseFilter, Pageable pageable  ){
		return this.financialReleaseRepository.resume(financialReleaseFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_FINANCIAL_RELEASE') and #oauth2.hasScope('read')")	
	public ResponseEntity<FinancialRelease> findById(@PathVariable Long id){
		return this.financialReleaseRepository.findById(id)
				.map(financial -> ResponseEntity.ok(financial))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_FINANCIAL_RELEASE') and #oauth2.hasScope('write')")
	public ResponseEntity<FinancialRelease> create (@Valid @RequestBody FinancialRelease financialRelease, HttpServletResponse response ){
		FinancialRelease savedFinancial = financialReleaseService.save(financialRelease);
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, savedFinancial.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFinancial);
		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_FINANCIAL_RELEASE') and #oauth2.hasScope('write')")
	public ResponseEntity<FinancialRelease> update(@PathVariable Long id, @Valid @RequestBody FinancialRelease financialRelease){
		
		try {
			FinancialRelease financialReleaseSaved =  financialReleaseService.update(id, financialRelease);
			return ResponseEntity.ok(financialReleaseSaved);	
		} catch (IllegalArgumentException  e) {
			return ResponseEntity.notFound().build();
		}
		
		 		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_FINANCIAL_RELEASE') and #oauth2.hasScope('write')")	
	public void delete(@PathVariable Long id) {
		this.financialReleaseRepository.deleteById(id);
	}
	
	
	@ExceptionHandler({PeopleNonExistentOrInactive.class})
	public ResponseEntity<Object> handlePeopleNonExistentOrInactive(PeopleNonExistentOrInactive ex){
		
		String userMessage = messageSource.getMessage("people.non-existent-or-inactive", null, LocaleContextHolder.getLocale());
		String devMessage =  Optional.ofNullable(ex.getCause()).orElse(ex).toString() ;
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return ResponseEntity.badRequest().body(errors);
		
	}
	
}
