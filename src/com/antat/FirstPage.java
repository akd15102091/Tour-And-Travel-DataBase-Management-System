package com.antat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstPage")
public class FirstPage extends HttpServlet {
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("header1.html").include(request, response); 
		request.getRequestDispatcher("header2.html").include(request, response); 
		request.getRequestDispatcher("adminUser.html").include(request, response); 


	}

}
