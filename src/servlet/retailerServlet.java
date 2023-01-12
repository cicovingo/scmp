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
	
	//Login olan sat�c�n�n �r�n listesi, ba�lang��ta bo�tur
	public List<Product> productList = new ArrayList<Product>();
	
	//Sat�c�n�n �r�n listesini �a��r�rken kullan�lan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productList = new Retailer().viewProducts();
	}

}