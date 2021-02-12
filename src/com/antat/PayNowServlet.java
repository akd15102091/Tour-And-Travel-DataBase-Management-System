package com.antat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/PayNowServlet")
public class PayNowServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter() ;
		
		String pac = request.getParameter("id3") ;
		HttpSession session = request.getSession() ;
		String pname = (String) session.getAttribute("usess");
		String pdate = request.getParameter("tdate") ;
		int padult = Integer.parseInt(request.getParameter("noa")) ;
		int pchild = Integer.parseInt(request.getParameter("noc")) ;
		String ptravel = request.getParameter("t") ;
		
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
		String query = "insert into BookingDetails values(?,?,?,?,?)";
		PreparedStatement pst = null ;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, pname);
			pst.setString(2, pac);
			pst.setInt(3, padult);
			pst.setInt(4, pchild);
			pst.setString(5, pdate);
			
			int cnt = pst.executeUpdate() ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String query2 = "select * from AddTourPackage where packageid='"+pac+"' ";
		Statement st = null ;
		ResultSet rs=null ;
		try {
			st = con.createStatement() ;
			rs = st.executeQuery(query2) ;
			rs.next() ;
			
			int total = 0;
			total = total + rs.getInt("stayamt") + rs.getInt("foodamt") ;
			if(ptravel.equals("Bus"))
			{
				total = total + rs.getInt("busamt") ;
			}
			if(ptravel.equals("Train"))
			{
				total = total + rs.getInt("trainamt") ;
			}
			if(ptravel.equals("AirLine"))
			{
				total = total + rs.getInt("airamt") ;
			}
			
			total = (total)*(padult) + (total)*(pchild)/2  ;
			
			request.getRequestDispatcher("header1.html").include(request, response);
			
			out.print("<br><br>") ;
			out.print("<center><table border='1' style='width:500px;'>");
			out.print("<tr style='background-color:grey;color:white'><td>Pay Online</td></tr>");
			out.print("<tr><td>Total Payable Amount:  "+total+"</td></tr>");
			out.print("<form action='LastPageServlet'>");
			out.print("<tr><td>Debit Card Number  : &emsp; <input type='text' ></td></tr>");
			out.print("<tr><td>Enter CVV  : &emsp; <input type='text'></td></tr>");
//			out.print("<tr><td><input type='submit' value='Pay'></td></tr>");

			out.print("</table></center><br>");
			out.print("<center><input type='submit' value='Pay Now'></center>");

			out.print("</form>");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			st.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.close() ;
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
