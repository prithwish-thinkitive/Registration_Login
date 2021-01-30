package com.Thinkitive;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowDetails")
public class ShowDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		PrintWriter write = response.getWriter();
		
		if(session != null) {
			write.print("<html>");
			write.print("<head>");
			write.print("<link rel='stylesheet' type='text/css' href='Style.css'");
			write.print("</head>");
			write.print("<body style='background-image: url(\"https://comrad.co.nz/wp-content/uploads/2019/08/Contact-form-background.jpg\")'>");
			write.println("<a href='Register.html'>Add Employees</a>");
			write.println("<a href='LogOut'>Log Out</a>");
			write.println("<h1 style='text-align: center;color:blue; font-size: 44px'>Employee List</h1>");
			
			MyDatabaseOperations op = new MyDatabaseOperations();
			
			write.print("<table class='hoverTable' style='width: 1700px; text-align: left; color: white; background-color: rgba(0, 0, 0, 0.5); border-radius: 25px; margin-left: auto; margin-right: auto; margin-top: 100px; box-shadow: 10px 10px rgb(48, 46, 46); padding-left: '20px;'>");
			write.print("<tr style='font-size: 30px; color: dodgerblue;'>");
			write.print("<th style='size: 5px;'>Name</th>");
			write.print("<th>Email</th>");
			write.print("<th>City</th>");
			write.print("<th>Country</th>");
			write.println("</tr>");
			
			List l = op.displayEmp();
			
			for(int index = 0; index < l.size(); ) {
				if(!l.get(index).equals("@")) {
					write.print("<tr>");
					write.print("<td>"+l.get(index ++)+"</td>");
					write.print("<td>"+l.get(index ++)+"</td>");
					write.print("<td>"+l.get(index ++)+"</td>");
					write.print("<td>"+l.get(index ++)+"</td>");
					write.println("</tr>");
			
				}
				else {
					write.println();
					index ++;
				}
			}
			
			write.print("</table>");
			write.print("</body>");
			write.print("</html>");
		}
		else {
			write.println("<h2 style='text-align: center; color:red; font-size: 24px''>Please Log In First !!</h2>");
			request.getRequestDispatcher("Login.html").include(request, response);
		}
	}

}
