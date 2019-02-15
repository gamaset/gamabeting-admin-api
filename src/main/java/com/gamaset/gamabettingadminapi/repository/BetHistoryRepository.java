package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.BetHistoryModel;

@Repository
public interface BetHistoryRepository  extends PagingAndSortingRepository<BetHistoryModel, Long> {

}
