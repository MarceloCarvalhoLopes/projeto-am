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

	public People save(People people) {
		people.getContacts().forEach(c -> c.setPeople(people));
		return peopleRepository.save(people);
	}
	
	public People update(Long id, People people) {
		People savedPeople = findPeopleById(id);

		savedPeople.getContacts().clear();
		savedPeople.getContacts().addAll(people.getContacts());
		savedPeople.getContacts().forEach(c -> c.setPeople(savedPeople));
		
		BeanUtils.copyProperties(people, savedPeople, "id", "contacts");
		return this.peopleRepository.save(savedPeople);
	}

	public void updatePropertyActive(Long id, Boolean active) {
		People savedPeople = findPeopleById(id);
		savedPeople.setActive(active);
		peopleRepository.save(savedPeople);
	}

	public People findPeopleById(Long id) {
		People savedPeople = peopleRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return savedPeople;
	}



}
