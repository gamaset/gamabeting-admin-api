package com.gamaset.gamabettingadminapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.AgentModel;

@Repository
public interface AgentRepository extends PagingAndSortingRepository<AgentModel, Long> {

	Optional<AgentModel> findByUserId(Long agentUserId);

	List<AgentModel> findByManagerUserId(Long userManagerId);

}
