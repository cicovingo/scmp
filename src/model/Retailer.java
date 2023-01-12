package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

/*
 * author: sukru.okul
 * */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Retailer extends User {
	@NonNull 
	private String name;
	@NonNull
	private String password;
	@NonNull
	private String emailAddress;
	@NonNull
	private String phoneNumber;

	//Satýcýnýn sepetindeki ürünlerin listesi
	private List<Product> orderList = new ArrayList<Product>();
	public List<Product> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Product> orderList) {
		this.orderList = orderList;
	}

	public Retailer(String emailAddress, String password) {
		super(emailAddress, password);
	}

	public void addOrder(Product product) {
		this.orderList.add(product);
	}

	public void deleteOrder(Product product) {
		this.orderList.remove(product);
	}

	//login için çaðýrýlan method
	public Retailer login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from retailer where emailAddress like'%" + emailAddress
					+ "%' and password like '%" + password + "%'");
			Retailer retailer = new Retailer();
			while (rs.next()) {
				retailer = new Retailer();
				retailer.setName(rs.getString("name"));
				retailer.setEmailAddress(rs.getString("emailAddress"));
				retailer.setPassword(rs.getString("password"));
				retailer.setPhoneNumber(rs.getString("phoneNumber"));
			}
			return retailer;
		} catch (Exception e) {
			return null;
		}
	}

	//register için çaðýrýlan method
	public boolean register() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into retailer(name,emailAddress,password,phone)values('" + name + "','"
					+ emailAddress + "','" + password + "','" + phoneNumber + "')");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//Ürünleri görüntülemek için çaðýrýlan method
	public List<Product> viewProducts() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from product");
			List<Product> productList = new ArrayList<Product>();
			Product product;
			while (rs.next()) {
				product = new Product();
				product.setProductId(rs.getLong("productId"));
				product.setProductName(rs.getString("productName"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setUserId(rs.getLong("userId"));
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			return new ArrayList<Product>();
		}
	}

	//Ürün satýn alma talebi yapýlýrken çaðýrýlan method, sepetteki ürünler alýnýrken çaðýrýlýr
	public void sellProducts() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			for (Product product : this.orderList) {
				int i = st.executeUpdate("insert into order(retailerId,orderDate,isApprove)values('" + this.emailAddress + "','"
						+ new Date() + "','" + false + "')");
				st.executeUpdate("insert into orderProduct(orderId,productId,quantity,totalAmount)values('" + i + "','"
						+ product.getProductId() + product.getQuantity() + "','" + product.getPrice() + "')");
			}
		} catch (Exception e) {
		}
	}
}
