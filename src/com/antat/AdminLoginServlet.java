package com.antat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("adminUsername");
		String password=request.getParameter("adminPassword");
		
		if(username.equals("admin") && password.equals("an@2514"))
		{
			HttpSession session = request.getSession();
			session.setAttribute("asess", "admin");
			
			request.getRequestDispatcher("header1.html").include(request, response); 
			request.getRequestDispatcher("header3.html").include(request, response); 
			request.getRequestDispatcher("addTourPackage.html").include(request, response); 
		}
		else
		{
			request.getRequestDispatcher("AdminServlet").include(request, response); 
			
		}
	}
}
