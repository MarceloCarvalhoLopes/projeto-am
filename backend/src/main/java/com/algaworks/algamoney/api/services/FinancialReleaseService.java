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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.algaworks.algamoney.api.dto.FinancialStatisticPerson;
import com.algaworks.algamoney.api.mail.Mailer;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.models.People;
import com.algaworks.algamoney.api.models.UserSystem;
import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;
import com.algaworks.algamoney.api.repositories.PeopleRepository;
import com.algaworks.algamoney.api.repositories.UserRepository;
import com.algaworks.algamoney.api.services.exception.PeopleNonExistentOrInactive;
import com.algaworks.algamoney.api.storage.S3;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class FinancialReleaseService {

	private static final String TO = "ROLE_SEARCH_FINANCIAL_RELEASE";
	
	private static final Logger logger = LoggerFactory.getLogger(FinancialReleaseService.class);
	
	@Autowired
	private PeopleRepository peopleRepository; 
	
	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private S3 s3;
	
	@Scheduled(cron = " 0 15 22 * * *")
	//@Scheduled(fixedDelay = 1000 * 60 * 30)
	public void notifyAboutOverdueEntries() {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Preparando envio de e-mails de aviso de lan??amentos vencidos.");	
		}
		
		List<FinancialRelease> overdue  = financialReleaseRepository
				.findByDueDateLessThanEqualAndPaymentDateIsNull(LocalDate.now());
		
		if (overdue.isEmpty()) {
			logger.info("Sem lan??amentos vencidos para aviso");
			return;
		}
		
		logger.info("Existe {} lancamentos vencidos.", overdue.size());

		List<UserSystem> to = userRepository.findByPermissionsDescription(TO);	
		
		if ( to.isEmpty()) {
			logger.warn("Existem lan??amentos vencidos, mas o sistema n??o encontrou destinat??rios.");
			return;
		}
		
		mailer.notifyFinancialOverdue(overdue, to);		
		
		logger.info("Envio de e-mail de aviso conclu??do.");
		
	}
	
	
	public byte[] reportByPerson(LocalDate first, LocalDate end) throws Exception{
		List<FinancialStatisticPerson> data = financialReleaseRepository.byPerson(first, end);
		
		Map<String, Object> parameters  = new HashMap<>();
		parameters.put("DT_INICIO", Date.valueOf(first));
		parameters.put("DT_FIM", Date.valueOf(end));
		parameters.put("REPORT_LOCALE", new Locale("pt","BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/reports/lauching-by-person.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, 
				new JRBeanCollectionDataSource(data));
		
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	public FinancialRelease save(@Valid FinancialRelease financialRelease) {
		Optional<People> people = peopleRepository.findById(financialRelease.getPeople().getId());
		if (!people.isPresent() || people.get().isInactive()) {
			throw new PeopleNonExistentOrInactive();
		}
		
		if (StringUtils.hasText(financialRelease.getAttach())) {
			s3.save(financialRelease.getAttach());
		}

		return financialReleaseRepository.save(financialRelease);
	}
	
	public FinancialRelease update(Long id, FinancialRelease financialRelease ) {
		FinancialRelease financialReleaseSaved = searchFinancialReleaseExisting(id); 
		if(!financialRelease.getPeople().equals(financialReleaseSaved.getPeople())) {
			validatePeople(financialRelease);
		}
		
		if (ObjectUtils.isEmpty(financialRelease.getAttach())
				&& StringUtils.hasText(financialReleaseSaved.getAttach())) {
			s3.delete(financialReleaseSaved.getAttach());
		}else if (StringUtils.hasText(financialRelease.getAttach())
				&& !financialRelease.getAttach().equals(financialReleaseSaved.getAttach())) {
			s3.update(financialReleaseSaved.getAttach(), financialRelease.getAttach());
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
