package com.Thinkitive;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Registration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter write = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		MyDatabaseOperations op = new MyDatabaseOperations();
		int status = op.insert(name, email, password, city, country);
		
		if(status > 0) {
			write.println("<h3 style='color:white'>Record successfully saved</h3>");
			request.getRequestDispatcher("Login.html").include(request, response);
		}
		else {
			write.println("<h3 style='color:red'>Error in saving record!!</h3>");
			request.getRequestDispatcher("Register.html").forward(request, response);
		}
	}

}
