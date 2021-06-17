package com.algaworks.algamoney.api.repositories.release;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;
import com.algaworks.algamoney.api.repositories.projections.FinancialReleaseResume;

public interface FinancialReleaseRepositoryQuery {

	public Page<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
	public Page<FinancialReleaseResume> resume(FinancialReleaseFilter financialReleaseFilter, Pageable pageable);
}
