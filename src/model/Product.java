package model;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
/*
 * author: sukru.okul
 * */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC) 
@ToString  
@Getter @Setter
public class Product{
	private  Long productId;
	private String productName;
	private int quantity;
	private double price;
	private Long userId;
	
	public List<Product> searchProductByName(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from product where productName like '%" + productName + "'");
			List<Product> productList = new ArrayList<Product>();
			Product product;
			while(rs.next()){
				product = new Product();
				product.setProductId(rs.getLong("productId"));
				product.setProductName(rs.getString("productName"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
			    productList.add(product);
			}
			return productList;
		}catch(Exception e){
			return new ArrayList<Product>();
		}
	}
	
	public Product viewProductDetails(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from product where productId='" + productId + "'");
			Product product = new Product();
			while(rs.next()){
				product = new Product();
				product.setProductId(rs.getLong("productId"));
				product.setProductName(rs.getString("productName"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
			}
			return product;
		}catch(Exception e){
			return new Product();
		}
	}
}
