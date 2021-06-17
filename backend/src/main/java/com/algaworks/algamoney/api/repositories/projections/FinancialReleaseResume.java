package com.algaworks.algamoney.api.repositories.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.models.FinancialType;

public class FinancialReleaseResume {

	private Long id;
	private String description;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private BigDecimal value;
	private FinancialType type;
	private String category;
	private String people;
	
	
	
	public FinancialReleaseResume(Long id, String description, LocalDate dueDate, LocalDate paymentDate,
			BigDecimal value, FinancialType type, String category, String people) {
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.type = type;
		this.category = category;
		this.people = people;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public FinancialType getType() {
		return type;
	}
	public void setType(FinancialType type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
	
}
