package com.algaworks.algamoney.api.services;

import java.util.Optional;

import javax.validation.Valid;

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

	
}
