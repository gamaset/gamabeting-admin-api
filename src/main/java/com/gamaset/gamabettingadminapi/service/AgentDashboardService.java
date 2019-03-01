package com.gamaset.gamabettingadminapi.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gamaset.gamabettingadminapi.endpoint.schema.AgentDashboard;
import com.gamaset.gamabettingadminapi.endpoint.schema.UserPrinciple;
import com.gamaset.gamabettingadminapi.repository.custom.AgentDashboardRepository;

import io.jsonwebtoken.lang.Objects;

@Service
public class AgentDashboardService {
	
	@Autowired
	private AgentDashboardRepository repository;

	public AgentDashboard buildDashboard() {
		AgentDashboard dashboard = new AgentDashboard();
		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Object[] dashboardData = repository.findDataDashboard(principle.getId());
		
		if(!Objects.isEmpty(dashboardData)) {
			dashboard.setTotalTipters((Integer)(dashboardData[0]));
			dashboard.setTotalBets((Integer) dashboardData[1]);
			dashboard.setTotalBetAmount(new BigDecimal(dashboardData[2].toString()));
		}
		
		System.out.println(dashboardData);
		
		return null;
	}

}
