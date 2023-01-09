package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
public class login extends HttpServlet {
	public login() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		if (username.isEmpty() || password.isEmpty()) {
			RequestDispatcher r = request.getRequestDispatcher("login.jsp");
			r.include(request, response);
		} else {
			if (usertype.equals("Satýcý")) {
				Retailer retailer = new Retailer(username, password);
				if(retailer.login()){
					RequestDispatcher r = request.getRequestDispatcher("retailer.jsp");
					r.forward(request, response);
				}else{
					
				}
			} else if (usertype.equals("Tedarikçi")) {
				Supplier supplier = new Supplier(username, password);
				if(supplier.login()){
					RequestDispatcher r = request.getRequestDispatcher("supplier.jsp");
					r.forward(request, response);
				}else{
					
				}
			} else {
				RequestDispatcher r = request.getRequestDispatcher("login.jsp");
				r.include(request, response);
			}
		}
	}

}