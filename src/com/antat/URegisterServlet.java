package com.antat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/URegisterServlet")
public class URegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String url = "jdbc:mysql://localhost:3306/khem";
		String un = "root" ;
		String pwd="Ashwini@1234" ;
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String uname = request.getParameter("name") ;
		String email = request.getParameter("email") ;	
		String password = request.getParameter("password") ;
		String dob = request.getParameter("date") ;
		String cn = request.getParameter("contact") ;
		if((uname!="") && (email!="") && (password!="") && (dob!="") && (cn!=""))
		{
			Connection con = null ;
			try {
				 con = DriverManager.getConnection(url, un, pwd) ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			PreparedStatement pst = null ;

			String query = "insert into tUserSignUp values(?,?,?,?,?)" ;
			
			try {
					pst = con.prepareStatement(query) ;
					pst.setString(1, uname);
					pst.setString(2, email);
					pst.setString(3, password);
					pst.setString(4, dob);
					pst.setString(5, cn);
					int cnt = pst.executeUpdate() ;

			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.getRequestDispatcher("UserLoginServlet").include(request, response);	 
			try {
				pst.close() ;
				con.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			request.getRequestDispatcher("UserSignUpServlet").include(request, response);
		}
		
	}
}
