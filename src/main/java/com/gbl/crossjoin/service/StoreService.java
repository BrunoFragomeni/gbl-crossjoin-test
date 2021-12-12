package com.gbl.crossjoin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbl.crossjoin.repository.StoreRepository;
import com.gbl.crossjoin.repository.domain.Store;

@Service
@Transactional
public class StoreService {

	@Autowired
	private StoreRepository repo;
	
	public List<Store> findAll() {
		return repo.findAll();
	}
}
