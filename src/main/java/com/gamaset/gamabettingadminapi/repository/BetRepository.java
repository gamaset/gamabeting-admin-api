package com.gamaset.gamabettingadminapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.BetModel;

@Repository
public interface BetRepository  extends PagingAndSortingRepository<BetModel, Long> {


	Optional<BetModel> findByIdAndCustomerAgentUserId(Long betId, Long userAgentId);

	List<BetModel> findByCustomerAgentUserId(Long userAgentId);

}
