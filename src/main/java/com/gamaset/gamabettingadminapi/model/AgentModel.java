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
@Table(name = "agente")
public class AgentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_agente")
	private Long id;

	@JoinColumn(name = "id_usuario_fk", nullable = false)
	@ManyToOne
	private UserModel user;

	@JoinColumn(name = "id_gerente_fk", nullable = false)
	@ManyToOne
	private ManagerModel manager;

	public AgentModel() {
	}

	public AgentModel(UserModel user, ManagerModel manager) {
		this.user = user;
		this.manager = manager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ManagerModel getManager() {
		return manager;
	}

	public void setManager(ManagerModel manager) {
		this.manager = manager;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
