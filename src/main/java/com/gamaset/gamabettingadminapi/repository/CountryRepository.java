package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.CountryModel;
import com.gamaset.gamabettingadminapi.model.ManagerModel;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<CountryModel, Long> {

}
