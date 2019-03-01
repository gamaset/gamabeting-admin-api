package com.gamaset.gamabettingadminapi.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gamaset.gamabettingadminapi.model.AgentModel;

public interface AgentDashboardRepository extends JpaRepository<AgentModel, Long> {
	
	@Query("SELECT distinct(c.id) AS NUM_APOSTADORES,  COUNT(bet) as NUM_APOSTAS_EFETIVAS, SUM(bet.betValue) AS TOTAL_VALOR_APOSTADO "
			+ "FROM BetModel bet "
			+ "INNER JOIN bet.customer c "
			+ "INNER JOIN c.agent.user u "
			+ "WHERE u.id = :agentUserId "
			+ "AND bet.status IN (1,3,4)")
	public Object[] findDataDashboard(@Param("agentUserId") Long agentUserId);

}
