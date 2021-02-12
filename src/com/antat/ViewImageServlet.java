package com.antat;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*
;@WebServlet("/ViewImageServlet")
public class ViewImageServlet extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		String id = request.getParameter("id");
		String query = "select img from AddTourPackage where packageid='"+id+"' " ;
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
			while(rs.next())
			{
//				 byte[] imageData = rs.getBytes("img");
//				  response.getOutputStream().write(imageData);
				Blob image=rs.getBlob(1) ;
				byte[ ] imgData = null ;
				imgData = image.getBytes(1,(int)image.length());
				response.setContentType("image/gif");

				OutputStream o = response.getOutputStream();

				o.write(imgData);

				o.flush();

				o.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
