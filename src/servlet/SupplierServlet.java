package servlet;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderProduct;
import model.Product;
import model.Supplier;
/*
 * author: sukru.okul
 * */
public class supplierServlet extends HttpServlet {
	public supplierServlet() {
		super();
	}
	
	//Tedarikçinin ürünlerinin listesi
	public List<Product> productList = new ArrayList<Product>();
	
	//Tedarikçinin sipariþleri görebilmek için oluþturulan ürün listesi
	public List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	
	//Sipariþler görüntülenmek istendiðinde çaðýrýlan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Yönlendirirken gönderdiðimiz userId ile Tedarikçi bulunur.
		Enumeration e = request.getAttributeNames();
		Long supplierId = (Long) request.getAttribute(e.nextElement().toString());
		Supplier supplier = new Supplier();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from supplier where userId= '" + supplierId );
			while (rs.next()) {
				supplier = new Supplier();
				supplier.setName(rs.getString("name"));
				supplier.setEmailAddress(rs.getString("emailAddress"));
				supplier.setPassword(rs.getString("password"));
				supplier.setPhoneNumber(rs.getString("phoneNumber"));
				supplier.setUserId(rs.getLong("userId"));
			}
		} catch (Exception e1) {
			System.out.println("Tedarikçi bulunamadý!!!");
		}
		
		//ilgili tedarikçinin sipariþleri çekilir
		orderProductList = supplier.getOrderProducts();
		
		//ilgili tedarikçinin ürünleri çekilir
		productList = supplier.getProducts();
	}

}