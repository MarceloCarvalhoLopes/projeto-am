package com.algaworks.algamoney.api.repositories.release;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.algaworks.algamoney.api.dto.FinancialStatisticalCategory;
import com.algaworks.algamoney.api.models.Category_;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.models.FinancialRelease_;
import com.algaworks.algamoney.api.models.People_;
import com.algaworks.algamoney.api.repositories.filter.FinancialReleaseFilter;
import com.algaworks.algamoney.api.repositories.projections.FinancialReleaseResume;

public class FinancialReleaseRepositoryImpl implements FinancialReleaseRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FinancialStatisticalCategory> byCategory(LocalDate monthReference) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<FinancialStatisticalCategory> criteriaQuery = criteriaBuilder.
				createQuery(FinancialStatisticalCategory.class);
		
		Root<FinancialRelease> root = criteriaQuery.from(FinancialRelease.class);
		
		criteriaQuery.select(criteriaBuilder.construct(FinancialStatisticalCategory.class, root.get(FinancialRelease_.category),
				criteriaBuilder.sum(root.get(FinancialRelease_.value))));
		
		LocalDate firstDay = monthReference.withMonth(1);
		LocalDate lastDay = monthReference.withDayOfMonth(monthReference.lengthOfMonth());
		
		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(FinancialRelease_.dueDate), 
						firstDay),
				criteriaBuilder.lessThanOrEqualTo(root.get(FinancialRelease_.dueDate), 
						lastDay));
		
		criteriaQuery.groupBy(root.get(FinancialRelease_.category));
		
		TypedQuery<FinancialStatisticalCategory> typedQuery = manager
				.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	@Override
	public Page<FinancialRelease> filter(FinancialReleaseFilter financialReleaseFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FinancialRelease> criteria = builder.createQuery(FinancialRelease.class);
		Root<FinancialRelease> root = criteria.from(FinancialRelease.class);

		Predicate[] predicates = createRestriction(financialReleaseFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<FinancialRelease> query = manager.createQuery(criteria);
		addPaginationRestriction(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(financialReleaseFilter));
		
	}
	
	@Override
	public Page<FinancialReleaseResume> resume(FinancialReleaseFilter financialReleaseFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FinancialReleaseResume> criteria = builder.createQuery(FinancialReleaseResume.class);
		Root<FinancialRelease> root = criteria.from(FinancialRelease.class);
		
		criteria.select(builder.construct(FinancialReleaseResume.class
				,	root.get(FinancialRelease_.id)
				,	root.get(FinancialRelease_.description)
				,	root.get(FinancialRelease_.dueDate)
				,	root.get(FinancialRelease_.paymentDate)
				,	root.get(FinancialRelease_.value)
				,	root.get(FinancialRelease_.type)
				,	root.get(FinancialRelease_.category).get(Category_.name)
				,	root.get(FinancialRelease_.people).get(People_.name)));

		
		
		Predicate[] predicates = createRestriction(financialReleaseFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<FinancialReleaseResume> query = manager.createQuery(criteria);
		addPaginationRestriction(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(financialReleaseFilter));
	}


	private Predicate[] createRestriction(FinancialReleaseFilter financialReleaseFilter, CriteriaBuilder builder,
			Root<FinancialRelease> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!ObjectUtils.isEmpty(financialReleaseFilter.getDescription()) ) {
			predicates.add(builder.like(
					builder.lower(root.get(FinancialRelease_.description)),"%" + financialReleaseFilter.getDescription().toLowerCase() +"%"));
		}

		
		if (financialReleaseFilter.getDueDateOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleaseFilter.getDueDateOf()));	
		}
		
		if (financialReleaseFilter.getDueDateBy() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleaseFilter.getDueDateBy()));
		}

		

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestriction(TypedQuery<?> query, Pageable pageable) {
		int currentPage =  pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordOnThePage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordOnThePage);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private Long total(FinancialReleaseFilter financialReleaseFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<FinancialRelease> root = criteria.from(FinancialRelease.class);
		
		Predicate[] predicates = createRestriction(financialReleaseFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}


}
