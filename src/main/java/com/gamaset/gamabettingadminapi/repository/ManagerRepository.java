package com.gamaset.gamabettingadminapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.gamabettingadminapi.model.Manager;

@Repository
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {

}
