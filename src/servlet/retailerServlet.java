package servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.Retailer;
import model.Supplier;

/*
 * author: sukru.okul
 * */
public class retailerServlet extends HttpServlet {
	public retailerServlet() {
		super();
	}

	// Login olan satýcýnýn ürün listesi, baþlangýçta boþtur
	public List<Product> productList = new ArrayList<Product>();

	// Satýcýnýn ürün listesini çaðýrýrken kullanýlan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Yönlendirirken gönderdiðimiz retailerId ile Satýcý bulunur.
		Enumeration e = request.getAttributeNames();
		String retailerId = (String) request.getAttribute(e.nextElement().toString());
		Retailer retailer = new Retailer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from retailer where emailAddress  like '%" + retailerId + "%'");
			while (rs.next()) {
				retailer = new Retailer();
				retailer.setName(rs.getString("name"));
				retailer.setEmailAddress(rs.getString("emailAddress"));
				retailer.setPassword(rs.getString("password"));
				retailer.setPhoneNumber(rs.getString("phoneNumber"));
			}
		} catch (Exception e1) {
			System.out.println("Satýcý bulunamadý!!!");
		}

		//Satýcýnýn satýn alabileceði ürünlerin listesi getirilir
		productList = retailer.viewProducts();
	}

}