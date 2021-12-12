package com.gbl.crossjoin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbl.crossjoin.repository.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p "
			+ "INNER JOIN p.storeProducts sp "
			+ "WHERE sp.store.id = :storeId "
			+ "ORDER BY p.id")
	List<Product> findByStore(@Param("storeId") Long storeId);
	
}
