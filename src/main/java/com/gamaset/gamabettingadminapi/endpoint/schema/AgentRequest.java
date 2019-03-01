package com.gamaset.gamabettingadminapi.endpoint.schema;

import java.math.BigDecimal;

public class AgentRequest extends UserRequest {

	private BigDecimal budget;

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

}
