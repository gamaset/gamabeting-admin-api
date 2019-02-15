package com.gamaset.gamabettingadminapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mercado")
public class MarketModel {

	@Id
	@Column(name = "id_mercado", nullable = false)
	private String id;

	@Column(name = "descricao", nullable = false)
	private String description;

	@Column(name = "odd", nullable = false)
	private Double odd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getOdd() {
		return odd;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}

}
