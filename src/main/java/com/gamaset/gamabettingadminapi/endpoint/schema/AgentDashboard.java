package com.gamaset.gamabettingadminapi.endpoint.schema;

import java.math.BigDecimal;

public class AgentDashboard {

	private Integer totalTipters;
	private Integer totalBets;
	private BigDecimal totalBetAmount;
	
	
	
	public void buildPartiallyResults(Integer totalTipters, Integer totalBets, BigDecimal totalBetAmount) {
		this.totalTipters = totalTipters;
		this.totalBets = totalBets;
		this.totalBetAmount = totalBetAmount;
	}
	
	public Integer getTotalTipters() {
		return totalTipters;
	}
	public void setTotalTipters(Integer totalTipters) {
		this.totalTipters = totalTipters;
	}
	public Integer getTotalBets() {
		return totalBets;
	}
	public void setTotalBets(Integer totalBets) {
		this.totalBets = totalBets;
	}
	public BigDecimal getTotalBetAmount() {
		return totalBetAmount;
	}
	public void setTotalBetAmount(BigDecimal totalBetAmount) {
		this.totalBetAmount = totalBetAmount;
	}
	
	
	
}
