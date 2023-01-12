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
@Getter
@Setter
public class Supplier extends User {
	private String name;
	private String password;
	private String emailAddress;
	private String phoneNumber;

	public Supplier(String emailAddress, String password) {
		super(emailAddress, password);
	}

	// login i�in �a��r�lan method
	public Supplier login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from supplier where emailAddress like '%" + emailAddress
					+ "%' and password like '%" + password + "%'");

			Supplier supplier = new Supplier();
			while (rs.next()) {
				supplier = new Supplier();
				supplier.setName(rs.getString("name"));
				supplier.setEmailAddress(rs.getString("emailAddress"));
				supplier.setPassword(rs.getString("password"));
				supplier.setPhoneNumber(rs.getString("phoneNumber"));
				supplier.setUserId(rs.getLong("userId"));
			}
			return supplier;
		} catch (Exception e) {
			return null;
		}
	}

	// register i�in �a��r�lan method
	public boolean register() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into supplier(name,emailAddress,password,phone)values('" + name + "','"
					+ emailAddress + "','" + password + "','" + phoneNumber + "')");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// sipari� edilen �r�nler toplu olarak onayland���nda �a��r�lan method
	public void updateProducts() {
		for (OrderProduct orderProduct : this.getOrderProducts()) {
			updateOrderProduct(orderProduct);
		}
	}

	// sipari� edilen bir �r�n onayland���nda �a��r�lan method
	public void updateOrderProduct(OrderProduct orderProduct) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			int i = st.executeUpdate("update orderProduct set isAprove=true where orderId=" + orderProduct.getOrderId()
					+ " and productId=" + orderProduct.getProductId());
			ResultSet rs = st.executeQuery("select p from product where p.prodcutId=" + orderProduct.getProductId());

			// Sipari�e g�re stok g�ncellenir
			while (rs.next()) {
				int newQuantity = rs.getInt("quantity") - orderProduct.getQuantity();
				st.executeUpdate("update product set quantity=" + newQuantity + " where productId="
						+ orderProduct.getProductId());
			}

			// Fatura olu�turmak i�in �r�n detaylar� �ekilir
			rs = st.executeQuery("select * from product where productId='" + orderProduct.getProductId() + "'");

			// Fatura objesi olu�turulur
			Billing billing = new Billing();
			while (rs.next()) {
				billing = new Billing();
				billing.setProductId(rs.getLong("productId"));
				billing.setProductName(rs.getString("productName"));
				billing.setProductQuantity(orderProduct.getQuantity());
				billing.setProductPrice(rs.getDouble("price"));
				billing.setTotalAmount(orderProduct.getTotalAmount());
				billing.generateBilling();
			}

		} catch (Exception e) {
			// stok yetersiz uyar�s�
			System.out.println(orderProduct.getProductId() + " i�in stok yetersiz!!!");
		}
	}

	// �r�nlerin sat�� i�in listelenirken �a��r�ld��� method
	public List<OrderProduct> getOrderProducts() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select op from order o join orderProduct op on o.orderId=op.orderId join product p on op.productId=p.productId where op.isAprove = false and p.userId="
							+ this.getUserId());
			List<OrderProduct> productList = new ArrayList<OrderProduct>();
			OrderProduct product;
			while (rs.next()) {
				product = new OrderProduct();
				product.setProductId(rs.getLong("productId"));
				product.setOrderId(rs.getLong("orderId"));
				product.setQuantity(rs.getInt("quantity"));
				product.setTotalAmount(rs.getDouble("totalAmount"));
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			return new ArrayList<OrderProduct>();
		}
	}

	// �r�nlerin listelenirken �a��r�ld��� method
	public List<Product> getProducts() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from product p where p.userId = "+ this.getUserId());
			List<Product> productList = new ArrayList<Product>();
			Product product;
			while (rs.next()) {
				product = new Product();
				product.setProductId(rs.getLong("productId"));
				product.setProductName(rs.getString("productName"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setUserId(this.getUserId());
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			return new ArrayList<Product>();
		}
	}
}
