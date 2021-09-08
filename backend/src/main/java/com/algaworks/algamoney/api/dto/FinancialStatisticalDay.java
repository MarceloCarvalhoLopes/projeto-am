package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.models.FinancialType;

public class FinancialStatisticalDay {

	private FinancialType type;
	private LocalDate day;
	private BigDecimal total;
	
	public FinancialStatisticalDay(FinancialType type, LocalDate day, BigDecimal total) {
		this.type = type;
		this.day = day;
		this.total = total;
	}

	public FinancialType getType() {
		return type;
	}

	public void setType(FinancialType type) {
		this.type = type;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	
	
}
