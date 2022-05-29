package com.sky.repository;

import java.util.List;

import com.sky.domain.Product;

public interface ProductRepository {
	public void addProduct(Product product);

	public List<Product> getProduct();
	public Product getProductById(String id);
	public void deleteById(String id);
	
	public void updateById(Product product);
}
