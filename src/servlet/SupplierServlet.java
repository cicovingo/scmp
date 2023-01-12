package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderProduct;
import model.Supplier;
/*
 * author: sukru.okul
 * */
public class SupplierServlet extends HttpServlet {
	public SupplierServlet() {
		super();
	}
	
	//Tedarik�inin sipari�leri g�rebilmek i�in olu�turulan �r�n listesi
	public List<OrderProduct> productList = new ArrayList<OrderProduct>();
	
	//Sipari�ler g�r�nt�lenmek istendi�inde �a��r�lan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productList = new Supplier().getProducts();
	}

}