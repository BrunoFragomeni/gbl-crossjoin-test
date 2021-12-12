package com.gbl.crossjoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbl.crossjoin.repository.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
}
