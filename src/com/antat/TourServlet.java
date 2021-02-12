package com.antat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TourServlet")
public class TourServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("header1.html").include(request, response); 
		request.getRequestDispatcher("header3.html").include(request, response); 
		request.getRequestDispatcher("addTourPackage.html").include(request, response); 
	}

}
