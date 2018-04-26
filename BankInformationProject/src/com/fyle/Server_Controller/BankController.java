package com.fyle.Server_Controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/server1")
public class BankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String bIfsc=request.getParameter("ifsc");
		String bName=request.getParameter("bn");
		String bCity=request.getParameter("ct");
		
		request.setAttribute("ifskey", bIfsc);
		request.setAttribute("bname", bName);
		request.setAttribute("bacity", bCity);
		
		PrintWriter out= response.getWriter();
		
		
		
	    if((bIfsc==null || bIfsc.equals("")) && ((bName==null || bName.equals("")) || bCity==null || bCity.equals("") ) )
			{
			out.print("* Either enter IFSC code or enter both Bank name and city!");
			RequestDispatcher  rd  = request.getRequestDispatcher("entry.html");
			rd.include(request,response);
			}
		 else if(bIfsc!=null && bIfsc!="")
		{
			if((bName!=null && bName!="")|| (bCity!=null && bCity!="") )
			{
				out.print("* Either enter IFSC code or enter both Bank name and city!");
				RequestDispatcher rd  = request.getRequestDispatcher("entry.html");
				rd.include(request,response);
			}
		    else if(bIfsc.length()<11 || bIfsc.length()>11) 
			 { 
				 out.print("enter a valid IFSC code");  
				 RequestDispatcher  rd = request.getRequestDispatcher("entry.html");
				 rd.include(request, response);
			 } 
			else if(ValidateData.checkUser(bIfsc,bName,bCity))
			{
				RequestDispatcher  rd  = request.getRequestDispatcher("BankDatabase");
			    rd.forward(request, response);
			}
			else
			{
				out.println("IFSC CODE does not exist");
				RequestDispatcher  rd = request.getRequestDispatcher("entry.html");
			    rd.include(request, response);
			}
		}
		else 
		{
			if(ValidateData.checkUser(bIfsc,bName,bCity))
			{
			RequestDispatcher  rd  = request.getRequestDispatcher("BankDatabase2");
			rd.forward(request,response);
			}
			else
			{
				out.println("Bank Name OR City does not exist");
				RequestDispatcher  rd = request.getRequestDispatcher("entry.html");
			    rd.include(request, response);
			}
		}
		
	}
	
	public void destroy() {
		super.destroy();
	}
}
