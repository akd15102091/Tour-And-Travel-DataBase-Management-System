package com.antat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LastPageServlet")
public class LastPageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header1.html").include(request, response);
		request.getRequestDispatcher("userHeader.html").include(request, response);

		out.print("<br><br><center><b><font size=15>Booking Confirmed</font></b></center>") ;
	}
}
