package com.gamaset.gamabettingadminapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long id;

	@Column(name = "desc_nome", nullable = false)
	private String name;

	@Column(name = "cpf")
	private Long taxId;

	@ManyToOne
	@JoinColumn(name = "id_agente_fk")
	private Agent agent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}

}
