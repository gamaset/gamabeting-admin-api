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
@Table(name = "cliente_dados_bancarios")
public class BankingDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_dado_bancario")
	private Long id;

	@Column(name = "agencia")
	private Long agencyNumber;

	@Column(name = "conta")
	private Long accountNumber;

	@ManyToOne
	@JoinColumn(name = "cod_banco", nullable = false)
	private Bank bank;

	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private CustomerModel customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Long getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(Long agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

}
