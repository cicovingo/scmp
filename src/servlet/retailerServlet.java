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

	// Login olan sat�c�n�n �r�n listesi, ba�lang��ta bo�tur
	public List<Product> productList = new ArrayList<Product>();

	// Sat�c�n�n �r�n listesini �a��r�rken kullan�lan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Y�nlendirirken g�nderdi�imiz retailerId ile Sat�c� bulunur.
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
			System.out.println("Sat�c� bulunamad�!!!");
		}

		//Sat�c�n�n sat�n alabilece�i �r�nlerin listesi getirilir
		productList = retailer.viewProducts();
	}

}