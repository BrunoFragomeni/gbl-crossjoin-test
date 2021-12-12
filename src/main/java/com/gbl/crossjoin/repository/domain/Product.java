package com.gbl.crossjoin.repository.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6885619510594868821L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "price")
	private Double price;
	
	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<StoreProduct> storeProducts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<StoreProduct> getStoreProducts() {
		return storeProducts;
	}

	public void setStoreProducts(List<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + "]";
	}
	
}
