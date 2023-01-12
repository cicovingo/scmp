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
	
	//Tedarikçinin sipariþleri görebilmek için oluþturulan ürün listesi
	public List<OrderProduct> productList = new ArrayList<OrderProduct>();
	
	//Sipariþler görüntülenmek istendiðinde çaðýrýlan method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productList = new Supplier().getProducts();
	}

}