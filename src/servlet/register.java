package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Retailer;
import model.Supplier;
/*
 * author: sukru.okul
 * */
public class register extends HttpServlet {
	public register() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailAddress = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		String phoneNumber = request.getParameter("phone");
		String name = request.getParameter("name");
		
		if (usertype.equals("Satýcý")) {
			Retailer retailer = new Retailer(name, password, emailAddress, phoneNumber);
			if(retailer.register()){
				RequestDispatcher r = request.getRequestDispatcher("login.jsp");
				r.forward(request, response);
			}else{
				
			}
		} else if (usertype.equals("Tedarikçi")) {
			Supplier supplier = new Supplier(name, password, emailAddress, phoneNumber);
			if(supplier.register()){
				RequestDispatcher r = request.getRequestDispatcher("login.jsp");
				r.forward(request, response);
			}else{
				
			}
		}
	}

}