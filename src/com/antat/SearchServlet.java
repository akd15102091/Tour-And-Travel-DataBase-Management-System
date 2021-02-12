package com.antat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();

		String str = request.getParameter("search") ;
		String str1 = str.toLowerCase() ;
		String cap = str1.substring(0, 1).toUpperCase() + str1.substring(1);
		String url = "jdbc:mysql://localhost:3306/khem";
		String un = "root" ;
		String pwd="Ashwini@1234" ;
		
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection con = null ;
		try {
			con = DriverManager.getConnection(url, un, pwd) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "select * from AddTourPackage where place='"+cap+"' " ;
		Statement st = null ;
		try {
			 st = (Statement) con.createStatement() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null ;
		try {
			rs = ((java.sql.Statement) st).executeQuery(query) ;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		int cnt=0 ;
		request.getRequestDispatcher("header1.html").include(request, response);
		request.getRequestDispatcher("userHeader.html").include(request, response);
		
		try {
			if(rs.next())
			{
				out.print("<center><table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'><td>PackageId</td><td>Place</td><td>Description</td><td>Stay Amount</td><td>Food Amount</td><td>Bus Amount</td><td>Train Amount</td><td>Airline Amount</td><td>Image</td><td>Booking</td></tr>");

				cnt++ ;
				out.print("<tr><td>"+rs.getString("packageid")+"</td><td>"+rs.getString("place")+"</td><td>"+rs.getString("des")+"</td><td>"+rs.getInt("stayamt")+"</td><td>"+rs.getInt("foodamt")+"</td><td>"+rs.getInt("busamt")+"</td><td>"+rs.getInt("trainamt")+"</td><td>"+rs.getInt("airamt")+"</td><td>");
				out.print("<form action='ViewImageServlet'>");
				out.print("<input type='text' name='id' value='"+rs.getString("packageid")+"'>");
				out.print("<input type='submit' value='viewImage' >");
				out.print("</form>");

				out.print("</td><td>");
				
				out.print("<form action='UserBookingServlet'>");
				out.print("<input type='text' name='id1' value='"+rs.getString("packageid")+"'>");
				out.print("<input type='submit' value='Book Now' >");
				out.print("</form>");
				
				out.print("</td></tr>");

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		out.print("</table></center>");
		try {
			st.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cnt==0)
		{
			request.getRequestDispatcher("search.html").include(request, response);

			request.getRequestDispatcher("ViewPlaceServlet").include(request, response);

		}
	}

	
}
