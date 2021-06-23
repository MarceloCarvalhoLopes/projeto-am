package com.algaworks.algamoney.api.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;
import com.algaworks.algamoney.api.repositories.PeopleRepository;
import com.algaworks.algamoney.api.services.exception.PeopleNonExistentOrInactive;

@Service
public class FinancialReleaseService {

	@Autowired
	private PeopleRepository peopleRepository; 
	
	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	
	
	public FinancialRelease save(@Valid FinancialRelease financialRelease) {
		Optional<People> people = peopleRepository.findById(financialRelease.getPeople().getId());
		if (!people.isPresent() || people.get().isInactive()) {
			throw new PeopleNonExistentOrInactive();
		}

		return financialReleaseRepository.save(financialRelease);
	}
	
	public FinancialRelease update(Long id, FinancialRelease financialRelease ) {
		FinancialRelease financialReleaseSaved = searchFinancialReleaseExisting(id); 
		if(!financialRelease.getPeople().equals(financialReleaseSaved.getPeople())) {
			validatePeople(financialRelease);
		}
		
		BeanUtils.copyProperties(financialRelease, financialReleaseSaved,"id");
		return financialReleaseRepository.save(financialReleaseSaved);
	}

	
	private void validatePeople(FinancialRelease financialRelease) {
		Optional<People> peopleOpt = null;
		if(financialRelease.getPeople().getId() != null) {
			peopleOpt = peopleRepository.findById(financialRelease.getPeople().getId());
		}
		
		if(peopleOpt == null || peopleOpt.isEmpty() || peopleOpt.get().isInactive() ) {
			throw new PeopleNonExistentOrInactive();
		}
	}

	private FinancialRelease searchFinancialReleaseExisting(Long id) {
		Optional<FinancialRelease>  financialReleaseOpt = financialReleaseRepository.findById(id);	
		return financialReleaseOpt.orElseThrow(() -> new IllegalArgumentException()) ;
	}

	
}
