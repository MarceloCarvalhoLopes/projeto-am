package com.algaworks.algamoney.api.services;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.dto.FinancialStatisticPerson;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;
import com.algaworks.algamoney.api.repositories.PeopleRepository;
import com.algaworks.algamoney.api.services.exception.PeopleNonExistentOrInactive;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class FinancialReleaseService {

	@Autowired
	private PeopleRepository peopleRepository; 
	
	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	public byte[] reportByPerson(LocalDate first, LocalDate end) throws Exception{
		List<FinancialStatisticPerson> data = financialReleaseRepository.byPerson(first, end);
		
		Map<String, Object> parameters  = new HashMap<>();
		parameters.put("DT_INICIO", Date.valueOf(first));
		parameters.put("DT_FIM", Date.valueOf(end));
		parameters.put("REPORT_LOCALE", new Locale("pt","BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"reports/lauching-by-person.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, 
				new JRBeanCollectionDataSource(data));
		
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
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
		
		if(peopleOpt == null || peopleOpt.get().isInactive() ) {
			throw new PeopleNonExistentOrInactive();
		}
	}

	private FinancialRelease searchFinancialReleaseExisting(Long id) {
		Optional<FinancialRelease>  financialReleaseOpt = financialReleaseRepository.findById(id);	
		return financialReleaseOpt.orElseThrow(() -> new IllegalArgumentException()) ;
	}

	
}
