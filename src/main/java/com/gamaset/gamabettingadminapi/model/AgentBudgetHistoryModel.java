package com.gamaset.gamabettingadminapi.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agente_orcamento_historico")
public class AgentBudgetHistoryModel extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_agente")
	private Long id;

	@JoinColumn(name = "id_agente_fk", nullable = false)
	@ManyToOne
	private AgentModel agent;

	@Column(name = "valor_limite_orcamento")
	private BigDecimal budget = new BigDecimal("0.00");

	public AgentBudgetHistoryModel() {
	}

	public AgentBudgetHistoryModel(AgentModel agent, BigDecimal budget) {
		this.agent = agent;
		this.budget = budget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AgentModel getAgent() {
		return agent;
	}

	public void setAgent(AgentModel agent) {
		this.agent = agent;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

}
