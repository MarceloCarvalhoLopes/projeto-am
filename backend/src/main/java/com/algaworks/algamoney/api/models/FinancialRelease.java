package com.algaworks.algamoney.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.algaworks.algamoney.api.repositories.listener.FinancialAttachListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@EntityListeners(FinancialAttachListener.class)
@Entity
@Table(name = "financial_release")
public class FinancialRelease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String description;
	
	@NotNull
	@Column(name= "DUE_DATE")
	//@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate dueDate;
	
	@Column(name= "PAYMENT_DATE")
	//@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate paymentDate;
	
	@NotNull
	private BigDecimal value;
	
	private String observation;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private FinancialType type;
	
	//Um lançamento tem uma categoria, mas esta cateogia pode estar em outros lançamentos
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	//Um lançamento tem uma pessoa, mas esta pessoa pode ter outros lançamentos
	@JsonIgnoreProperties("contacts")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	private People people;

	private String attach;
	
	@Transient
	private String urlAttach;
	
	@JsonIgnore
	public boolean isReceipt() {
		return FinancialType.RECEIPT.equals(this.type);
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

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public FinancialType getType() {
		return type;
	}

	public void setType(FinancialType type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
	
	

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getUrlAttach() {
		return urlAttach;
	}

	public void setUrlAttach(String urlAttach) {
		this.urlAttach = urlAttach;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinancialRelease other = (FinancialRelease) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
