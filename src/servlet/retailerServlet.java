package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.Retailer;
/*
 * author: sukru.okul
 * */
public class retailerServlet extends HttpServlet {
	public retailerServlet() {
		super();
	}
	public List<Product> productList = new ArrayList<Product>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productList = new Retailer().viewProducts();
	}

}