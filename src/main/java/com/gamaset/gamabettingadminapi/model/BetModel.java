package com.gamaset.gamabettingadminapi.model;

import static com.gamaset.gamabettingadminapi.model.BetStatusEnum.PENDING;
import static com.gamaset.gamabettingadminapi.utils.CalculatorUtils.getDifference;
import static com.gamaset.gamabettingadminapi.utils.CalculatorUtils.getMultiply;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;

import com.gamaset.gamabettingadminapi.endpoint.schema.BetRequest;
import com.gamaset.gamabettingadminapi.utils.HashUtils;

@Entity
@Table(name = "aposta")
public class BetModel extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aposta")
	private Long id;
	@Column(name = "id_aposta_hash", nullable = false, unique = true)
	private String hashId;
	@Column(name = "valor_aposta", nullable = false)
	private BigDecimal betValue;
	@Column(name = "valor_retorno_esperado_com_comissao", nullable = false)
	private BigDecimal expectedValueDiscountCommission;
	@Column(name = "valor_comissao", nullable = false)
	private BigDecimal commissionValue;
	@Column(name = "porcentagem_comissao", nullable = false)
	private BigDecimal commissionPercent;
	@Column(name = "total_odd", nullable = false)
	private Double totalOdd;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	private BetStatusEnum status;
	@JoinColumn(name = "id_cliente_fk", nullable = false)
	@ManyToOne
	private CustomerModel customer;

	public BetModel(BetRequest request, CustomerModel customer, BigDecimal commissionPercentConfig) {
		this.hashId = HashUtils.generateHashId();
		this.commissionPercent = commissionPercentConfig;
		this.betValue = request.getBetValue();
		calculateRelatedValues(request.getEvents());
		this.status = PENDING;
		this.customer = customer;
	}
	
	public BetModel() {
	}

	public void calculateRelatedValues(List<EventModel> events) {
		if (Objects.nonNull(getBetValue())) {
			this.totalOdd = events.stream().mapToDouble(e -> e.getMarket().getOdd()).reduce(1, (a, b) ->  a * b);
			BigDecimal profitValue = getMultiply(getBetValue(), new BigDecimal(getTotalOdd()));
			this.commissionValue = getMultiply(profitValue, getCommissionPercent());
			this.expectedValueDiscountCommission = getDifference(getCommissionValue(), profitValue);
		}
	}

	public BigDecimal getBetValue() {
		return betValue;
	}

	public void setBetValue(BigDecimal betValue) {
		this.betValue = betValue;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHashId() {
		return hashId;
	}

	public BigDecimal getExpectedValueDiscountCommission() {
		return expectedValueDiscountCommission;
	}

	public BigDecimal getCommissionValue() {
		return commissionValue;
	}

	public BigDecimal getCommissionPercent() {
		return this.commissionPercent;
	}

	public Double getTotalOdd() {
		return totalOdd;
	}

	public BetStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BetStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
