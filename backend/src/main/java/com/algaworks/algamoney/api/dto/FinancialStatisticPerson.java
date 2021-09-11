package com.algaworks.algamoney.api.dto;

import java.math.BigDecimal;

import com.algaworks.algamoney.api.models.FinancialType;
import com.algaworks.algamoney.api.models.People;

public class FinancialStatisticPerson {

	private FinancialType type;
	
	private People people;
	
	private BigDecimal total;

	public FinancialStatisticPerson(FinancialType type, People people, BigDecimal total) {
		this.type = type;
		this.people = people;
		this.total = total;
	}

	public FinancialType getType() {
		return type;
	}

	public void setType(FinancialType type) {
		this.type = type;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	
	
	
	
}
