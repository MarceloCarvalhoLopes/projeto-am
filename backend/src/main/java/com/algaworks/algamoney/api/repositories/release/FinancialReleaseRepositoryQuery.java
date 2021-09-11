package com.algaworks.algamoney.api.repositories.release;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.dto.FinancialStatisticCategory;
import com.algaworks.algamoney.api.dto.FinancialStatisticDay;
import com.algaworks.algamoney.api.dto.FinancialStatisticPerson;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;
import com.algaworks.algamoney.api.repositories.projections.FinancialReleaseResume;

public interface FinancialReleaseRepositoryQuery {
	
	public List<FinancialStatisticPerson> byPerson(LocalDate start, LocalDate end);
	public List<FinancialStatisticCategory> byCategory(LocalDate monthReference);
	public List<FinancialStatisticDay> byDay(LocalDate monthReference);

	public Page<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
	public Page<FinancialReleaseResume> resume(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
}
