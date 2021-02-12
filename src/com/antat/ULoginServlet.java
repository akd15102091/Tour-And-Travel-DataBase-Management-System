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
import javax.servlet.http.HttpSession;
@WebServlet("/ULoginServlet")
public class ULoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/khem";
		String un = "root" ;
		String pwd="Ashwini@1234" ;
		String uname=request.getParameter("loginUsername");
		String pass=request.getParameter("loginPassword");
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
		String query = "select * from tUserSignUp where email='"+uname+"' and upassword='"+pass+"' ";
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
		try {
			if(rs.next())
			{
				HttpSession session = request.getSession() ;
				session.setAttribute("usess", uname);
				
				request.getRequestDispatcher("header1.html").include(request, response);
				request.getRequestDispatcher("userHeader.html").include(request, response);
				request.getRequestDispatcher("search.html").include(request, response);

				request.getRequestDispatcher("ViewPlaceServlet").include(request, response);

			}
			else
			{
				request.getRequestDispatcher("UserLoginServlet").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
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