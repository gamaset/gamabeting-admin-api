package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.CustomerModel;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerModel, Long> {

}
