package com.gbl.crossjoin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbl.crossjoin.exception.BusinessException;
import com.gbl.crossjoin.exception.NotFoundCustomException;
import com.gbl.crossjoin.repository.domain.Product;
import com.gbl.crossjoin.repository.domain.Store;
import com.gbl.crossjoin.service.ProductService;
import com.gbl.crossjoin.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreApi {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public ResponseEntity<List<Store>> findAll(){

		List<Store> stores = storeService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(stores);
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Product>> findProducts(@PathVariable Long id) throws BusinessException, NotFoundCustomException{
		
		List<Product> products = productService.findByStore(id);
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	
}
