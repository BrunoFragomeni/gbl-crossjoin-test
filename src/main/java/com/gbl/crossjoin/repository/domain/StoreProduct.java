package com.gbl.crossjoin.repository.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gbl.crossjoin.repository.domain.id.StoreProductId;


@Entity
@Table(name = "store_product")
public class StoreProduct implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4457258525887534500L;

	@EmbeddedId
	private StoreProductId id;
	
	@ManyToOne
    @MapsId("idStore")
    @JoinColumn(name = "store_id")
	@JsonManagedReference
    Store store;
	
	@ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "product_id")
	@JsonManagedReference
    Product product;
	
	@Column(name = "stock")
	private Long stock;

	public StoreProductId getId() {
		return id;
	}

	public void setId(StoreProductId id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, product, stock, store);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof StoreProduct)) {
			return false;
		}
		StoreProduct other = (StoreProduct) obj;
		return Objects.equals(id, other.id) && Objects.equals(product, other.product)
				&& Objects.equals(stock, other.stock) && Objects.equals(store, other.store);
	}

	@Override
	public String toString() {
		return "StoreProduct [id=" + id + ", store=" + store + ", product=" + product + ", stock=" + stock + "]";
	}
	
}
