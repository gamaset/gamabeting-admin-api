package com.gamaset.gamabettingadminapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gerente")
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_gerente")
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@Column(name = "apelido", nullable = false, unique = true)
	private String nickname;

	@Column(name = "cpf", nullable = false, unique = true)
	private String taxId;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate birthDate;

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

}
