package com.gamaset.gamabettingadminapi.model;

import java.time.LocalDate;

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
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_agente")
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@Column(name = "apelido", nullable = false, unique = true)
	private String nickname;
	
	@Column(name = "cpf", nullable = false, unique = true)
	private String taxId;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate birthDate;
	
	@JoinColumn(name = "id_gerente_fk", nullable = false)
	@ManyToOne
	private Manager manager;

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

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
