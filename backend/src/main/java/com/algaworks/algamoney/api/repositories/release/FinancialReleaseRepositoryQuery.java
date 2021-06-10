package com.algaworks.algamoney.api.repositories.release;

import java.util.List;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;

public interface FinancialReleaseRepositoryQuery {

	public List<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter);
}
