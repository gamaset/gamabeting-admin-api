package com.gamaset.gamabettingadminapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agente")
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_agente")
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "apelido")
	private String nickname;

	@Column(name = "tipo_agente")
	private AgentType agentType;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public AgentType getAgentType() {
		return agentType;
	}

	public void setAgentType(AgentType agentType) {
		this.agentType = agentType;
	}

}
