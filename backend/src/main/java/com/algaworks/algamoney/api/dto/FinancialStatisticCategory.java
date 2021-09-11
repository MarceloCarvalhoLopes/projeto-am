package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;

import com.algaworks.algamoney.api.models.Category;

public class FinancialStatisticCategory {

	private Category category;
	private BigDecimal total;
	
	public FinancialStatisticCategory(Category category, BigDecimal total) {
		this.category = category;
		this.total = total;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
}
