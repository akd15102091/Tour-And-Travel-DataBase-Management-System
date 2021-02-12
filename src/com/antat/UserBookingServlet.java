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
@WebServlet("/UserBookingServlet")
public class UserBookingServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String url = "jdbc:mysql://localhost:3306/khem";
		String un = "root" ;
		String pwd="Ashwini@1234" ;
		String str=request.getParameter("id1");
		
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
		String query = "select * from AddTourPackage where packageid='"+str+"' ";
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
		request.getRequestDispatcher("userHeader.html").include(request, response);
		out.print("<center><table border='1' style='width:700px;'>");
		out.print("<tr style='background-color:grey;color:white'><td>Booking Details</td></tr>");
		try {
			if(rs.next())
			{
				out.print("<form action='PayNowServlet'>");
				out.print("<tr><td>Package Id  :  <input type='text' name='id3' value='"+rs.getString("packageid")+"'></td></tr>");

				out.print("<tr><td>Name  :  "+rs.getString("place")+"</td></tr>");
				out.print("<tr><td>Description  :  "+rs.getString("des")+"</td></tr>");
				out.print("<tr><td>"+rs.getString("days")+ " Days & "+(rs.getInt("days") -1)+" Nights "+"</td></tr>");
				out.print("<tr><td>Number Of Adults:  <input type='text' name='noa'> &emsp;&emsp; Number Of Children:  <input type='text' name='noc'> </td></tr>");
				out.print("<tr><td>Stay Amount  :  "+rs.getInt("stayamt")+"</td></tr>");
				out.print("<tr><td>Food Amount  :  "+rs.getInt("foodamt")+"</td></tr>");
				out.print("<tr><td>Travelling:<br>&emsp;<input type='radio' value='Train' name='t'>Train<input type='radio' value='Bus' name='t'>Bus<input type='radio' value='AirLine' name='t'>AirLine</td></tr>");
				out.print("<tr><td>Date  :  <input type='date' name='tdate'></td></tr>");
				out.print("</table> <br><br>");

				out.print("<input type='submit' value='Confirm Booking'>");

				out.print("</form>");
				out.print("</center>");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
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