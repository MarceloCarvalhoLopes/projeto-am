package com.algaworks.algamoney.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.release.FinancialReleaseRepositoryQuery;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long>, FinancialReleaseRepositoryQuery  {

	List<FinancialRelease> findByDueDateLessThanEqualAndPaymentDateIsNull(LocalDate date);
	
}
