package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.AgentBudgetHistoryModel;

@Repository
public interface AgentBudgetHistoryRepository extends PagingAndSortingRepository<AgentBudgetHistoryModel, Long> {

}
