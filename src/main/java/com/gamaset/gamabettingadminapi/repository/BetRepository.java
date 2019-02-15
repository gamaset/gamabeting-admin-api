package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.BetModel;

@Repository
public interface BetRepository  extends PagingAndSortingRepository<BetModel, Long> {

}
