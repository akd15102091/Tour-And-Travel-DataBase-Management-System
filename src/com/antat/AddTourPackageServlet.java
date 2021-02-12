package com.antat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part ;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.sql.*;
@WebServlet("/AddTourPackageServlet")
@MultipartConfig(maxFileSize = 16177215) 
public class AddTourPackageServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String url = "jdbc:mysql://localhost:3306/khem";
		String un = "root" ;
		String pwd="Ashwini@1234" ;
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null ;
		try {
			 con = DriverManager.getConnection(url, un, pwd) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String pi = request.getParameter("packageId") ;
		String place = request.getParameter("placeName") ;
		String des = request.getParameter("description") ;
		int stayamt = Integer.parseInt(request.getParameter("stayAmount")) ;
		int foodamt = Integer.parseInt(request.getParameter("foodAmount")) ;
		int busamt = Integer.parseInt(request.getParameter("busAmount")) ;
		int trainamt = Integer.parseInt(request.getParameter("trainAmount")) ;
		int airamt = Integer.parseInt(request.getParameter("airlineAmount")) ;
		int days = Integer.parseInt(request.getParameter("numofdays")) ;
		InputStream inputStream = null; 
		
        Part filePart = request.getPart("image");
        if (filePart != null) 
        {
               inputStream = filePart.getInputStream();
        }
//		FileInputStream fts =null ;
//		try {
//		fts=new FileInputStream((getParameter("image")));
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
        PreparedStatement pst = null ;

		String query = "insert into AddTourPackage values(?,?,?,?,?,?,?,?,?,?)" ;
		
		try {
				pst = con.prepareStatement(query) ;
				pst.setString(1, pi );
				pst.setString(2, place);
				pst.setString(3, des);
				pst.setInt(4, stayamt);
				pst.setInt(5, foodamt);
				pst.setInt(6, busamt);
				pst.setInt(7, trainamt);
				pst.setInt(8, airamt);
				pst.setInt(9, days);
				if (inputStream != null) 
				{    
	                pst.setBlob(10, inputStream);
	            }
				//pst.setBlob(12, fts);
				//try{fts.close();} catch(Exception e){e.printStackTrace();}
				
				int cnt = pst.executeUpdate() ;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pst.close() ;
			con.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		request.getRequestDispatcher("header1.html").include(request, response); 
		request.getRequestDispatcher("header3.html").include(request, response); 
		request.getRequestDispatcher("addTourPackage.html").include(request, response);
		
		
	}

	private String getParameter(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
