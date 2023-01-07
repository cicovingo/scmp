package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
				RequestDispatcher r = request.getRequestDispatcher("retailer.jsp");
				r.forward(request, response);
			} else if (usertype.equals("Tedarikçi")) {
				RequestDispatcher r = request.getRequestDispatcher("supplier.jsp");
				r.forward(request, response);
			} else {
				RequestDispatcher r = request.getRequestDispatcher("login.jsp");
				r.include(request, response);
			}
		}
	}

}