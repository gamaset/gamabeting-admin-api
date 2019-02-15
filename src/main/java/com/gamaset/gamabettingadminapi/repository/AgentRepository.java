package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.AgentModel;

@Repository
public interface AgentRepository extends PagingAndSortingRepository<AgentModel, Long> {

}
