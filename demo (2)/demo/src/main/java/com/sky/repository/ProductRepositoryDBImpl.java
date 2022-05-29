package com.sky.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sky.domain.Product;

@Repository("ProductRepositoryDBImpl")
public class ProductRepositoryDBImpl implements ProductRepository 
{
	
	private static String INSERT_PRODUCT_SQL="insert into product(product_ID,product_Name,product_price) values(?,?,?)";
   
	private static String ALL_PRODUCT_SQL="select product_ID,product_Name,product_price from product";
	private static String GET_PRODUCT_ID="select product_ID,product_Name,product_price from product WHERE  product_ID=?";
	
	static String DELETE_PRODUCT_ID="delete product where product_ID=?";
	static String UPDATE_PRODUCT_ID="update product set  product_Name=?,product_price=?where product_ID=?";
	
	
	@Autowired
 private JdbcTemplate jdbcTemplate;
	@Override
	public void addProduct(Product product) {
		jdbcTemplate.update(INSERT_PRODUCT_SQL,
		product.getProductId(),
		product.getProductName(),
		product.getPrice());
		
	}

	@Override
	public List<Product> getProduct() {
		return this.jdbcTemplate.query(ALL_PRODUCT_SQL,new ProductRowMapper() );
		
	}
   private static final class ProductRowMapper implements org.springframework.jdbc.core.RowMapper<Product>
   {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product product=new Product();
		product.setProductId(rs.getString("product_ID"));
		product.setProductName(rs.getString("product_Name"));
		product.setPrice(rs.getInt("product_price"));
		
		return product;
	}
	   
   }
   
   
	@Override
	public Product getProductById(String id) {
		
		 return this.jdbcTemplate.queryForObject(GET_PRODUCT_ID, new Object[]{id}, new ProductRowMapper());

				  
	}

	@Override
	public void deleteById(String id) {
		
		  this.jdbcTemplate.update(DELETE_PRODUCT_ID, id);
	}

	@Override
	public void updateById(Product product) {
		
		//this.jdbcTemplate.update(UPDATE_PRODUCT_ID,product.getProductId());
		jdbcTemplate.update(UPDATE_PRODUCT_ID,
				product.getProductName(),
				product.getPrice(),
				product.getProductId());
				
	}

}
