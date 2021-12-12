package com.gbl.crossjoin.service;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbl.crossjoin.exception.BusinessException;
import com.gbl.crossjoin.exception.NotFoundCustomException;
import com.gbl.crossjoin.repository.ProductRepository;
import com.gbl.crossjoin.repository.StoreRepository;
import com.gbl.crossjoin.repository.domain.Product;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	public List<Product> findByStore(Long idStore) throws BusinessException, NotFoundCustomException {
		
		if(storeRepository.findById(idStore).isEmpty()) {
			throw new BusinessException(MessageFormat.format("Store with id {0} does not exist", idStore));
		};
		
		List<Product> products;
		products = productRepository.findByStore(idStore);
		if(products.isEmpty()) {
			throw new NotFoundCustomException(MessageFormat.format("Store with id {0} does not have any product", idStore));
		}
		return products;
	}
}
