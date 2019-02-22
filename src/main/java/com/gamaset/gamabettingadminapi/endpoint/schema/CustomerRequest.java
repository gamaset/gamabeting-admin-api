package com.gamaset.gamabettingadminapi.endpoint.schema;

public class CustomerRequest extends UserRequest {

	private Long agentId;

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long managerId) {
		this.agentId = managerId;
	}

}
