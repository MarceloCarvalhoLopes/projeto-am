package com.algaworks.algamoney.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.repositories.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	private PeopleRepository peopleRepository;

	public People update(Long id, People people) {
		People savedPeople = peopleRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(people, savedPeople,"id");
		
		return this.peopleRepository.save(savedPeople);
	}
}
