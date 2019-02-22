package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.ManagerModel;

@Repository
public interface ManagerRepository extends PagingAndSortingRepository<ManagerModel, Long> {

	public ManagerModel findByUserId(Long userId);
}
