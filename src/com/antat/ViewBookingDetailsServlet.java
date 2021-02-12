package com.antat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
@WebServlet("/ViewBookingDetailsServlet")
public class ViewBookingDetailsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();

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
		String query = "select * from BookingDetails " ;
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
		request.getRequestDispatcher("header1.html").include(request, response);
		request.getRequestDispatcher("header3.html").include(request, response);

		out.print("<br><br>");
		out.print("<center><table border='1' style='width:700px;'>");
		out.print("<tr style='background-color:grey;color:white'><td>User Email</td><td>Package ID</td><td>Number Of Adults</td><td>Number Of Children</td><td>Date</td></tr>");
		try {
			while(rs.next())
			{
				
				out.print("<tr><td>"+rs.getString("username")+"</td><td>"+rs.getString("packageid")+"</td><td>"+rs.getInt("noa")+"</td><td>"+rs.getInt("noc")+"</td><td>"+rs.getString("dates")+"</td></tr>");

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
	}
}
