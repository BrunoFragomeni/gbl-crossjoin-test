package com.gbl.crossjoin.repository.domain.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StoreProductId implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2864356510029275053L;

	@Column(name = "store_id")
	private Long idStore;

	@Column(name = "product_id")
	private Long idProduct;

	public StoreProductId() {
	}

	public StoreProductId(Long idStore, Long idProduct) {
		this.idStore = idStore;
		this.idProduct = idProduct;
	}

	public Long getIdStore() {
		return idStore;
	}

	public void setIdStore(Long idStore) {
		this.idStore = idStore;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduct, idStore);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof StoreProductId)) {
			return false;
		}
		StoreProductId other = (StoreProductId) obj;
		return Objects.equals(idProduct, other.idProduct) && Objects.equals(idStore, other.idStore);
	}

	@Override
	public String toString() {
		return "StoreProductId [idStore=" + idStore + ", idProduct=" + idProduct + "]";
	}
	

}