package com.gamaset.gamabettingadminapi.endpoint.schema;

import java.math.BigDecimal;
import java.util.List;

import com.gamaset.gamabettingadminapi.model.EventModel;

public class BetRequest {

	private BigDecimal betValue;
	private Long customerId;
	private List<EventModel> events;

	public BigDecimal getBetValue() {
		return betValue;
	}

	public void setBetValue(BigDecimal betValue) {
		this.betValue = betValue;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<EventModel> getEvents() {
		return events;
	}

	public void setEvents(List<EventModel> events) {
		this.events = events;
	}

}
