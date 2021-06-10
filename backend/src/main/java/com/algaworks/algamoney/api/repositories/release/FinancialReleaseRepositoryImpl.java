package com.algaworks.algamoney.api.repositories.release;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.models.FinancialRelease_;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;

public class FinancialReleaseRepositoryImpl implements FinancialReleaseRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FinancialRelease> criteria = builder.createQuery(FinancialRelease.class);
		Root<FinancialRelease> root = criteria.from(FinancialRelease.class);

		Predicate[] predicates = createRestriction(financialReleaseFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<FinancialRelease> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] createRestriction(FinancialReleaseFilter financialReleaseFilter, CriteriaBuilder builder,
			Root<FinancialRelease> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!ObjectUtils.isEmpty(financialReleaseFilter.getDescryption()) ) {
			predicates.add(builder.like(
					builder.lower(root.get(FinancialRelease_.description)),"%" + financialReleaseFilter.getDescryption().toLowerCase() +"%"));
		}

		if (financialReleaseFilter.getDueDateOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleaseFilter.getDueDateOf()));	
		}
		
		if (financialReleaseFilter.getDueDateBy() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleaseFilter.getDueDateBy()));
		}

		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
