package com.algaworks.algamoney.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.repositories.release.FinancialReleaseRepositoryQuery;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long>, FinancialReleaseRepositoryQuery  {

}
