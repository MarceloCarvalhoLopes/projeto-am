package com.algaworks.algamoney.api.repositories.release;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.dto.FinancialStatisticalCategory;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;
import com.algaworks.algamoney.api.repositories.projections.FinancialReleaseResume;

public interface FinancialReleaseRepositoryQuery {
	
	public List<FinancialStatisticalCategory> byCategory(LocalDate monthReference);

	public Page<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
	public Page<FinancialReleaseResume> resume(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
}
