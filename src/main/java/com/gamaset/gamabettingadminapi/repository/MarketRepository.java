package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.MarketModel;

@Repository
public interface MarketRepository extends PagingAndSortingRepository<MarketModel, Long> {

}
