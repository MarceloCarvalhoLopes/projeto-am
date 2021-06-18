package com.algaworks.algamoney.api.repositories.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FinancialReleaseFilter {

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateOf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateBy;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDateOf() {
		return dueDateOf;
	}
	public void setDueDateOf(LocalDate dueDateOf) {
		this.dueDateOf = dueDateOf;
	}
	public LocalDate getDueDateBy() {
		return dueDateBy;
	}
	public void setDueDateBy(LocalDate dueDateBy) {
		this.dueDateBy = dueDateBy;
	}
	
		
}
