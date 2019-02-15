package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.AgentModel;
import com.gamaset.gamabettingadminapi.model.CompetitionModel;

@Repository
public interface CompetitionRepository extends PagingAndSortingRepository<CompetitionModel, Long> {

}
