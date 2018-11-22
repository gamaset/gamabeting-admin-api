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
@Table(name = "agente_colaborador")
public class AgentCooperator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_agente_colaborador")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_agenciador_fk")
	private Agent broker;
	@ManyToOne
	@JoinColumn(name = "id_colaborador_fk")
	private Agent cooperator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Agent getBroker() {
		return broker;
	}

	public void setBroker(Agent broker) {
		this.broker = broker;
	}

	public Agent getCooperator() {
		return cooperator;
	}

	public void setCooperator(Agent cooperator) {
		this.cooperator = cooperator;
	}

}
