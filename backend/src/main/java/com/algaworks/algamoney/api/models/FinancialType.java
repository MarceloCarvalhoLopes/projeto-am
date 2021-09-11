package com.algaworks.algamoney.api.models;

public enum FinancialType {

	RECEIPT ("Receita"),
	EXPENSE ("Despesa");
	
	private final String description;
	
	
	FinancialType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
	
}
