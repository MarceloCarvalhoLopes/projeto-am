package com.algaworks.algamoney.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.FinancialRelease;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long> {

}
