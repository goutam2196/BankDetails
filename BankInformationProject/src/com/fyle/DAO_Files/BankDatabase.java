package com.fyle.DAO_Files;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fyle.DbConnection_Files.DbConnector;

@WebServlet("/BankDatabase")
public class BankDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sql1 = "SELECT * FROM bank_locator.bank_branches_csv where ifsc=?";
	String code,bnch,add,cty,dst,ste,bnkn;
	int id; 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String ifsc=(String) request.getAttribute("ifskey");
		response.setContentType("text/html");
		
		 Connection con = null;
		 PreparedStatement preparedStatement = null;
		try {
			con=DbConnector.createConnection();
		    preparedStatement = con.prepareStatement(sql1);
		    preparedStatement.setString(1, ifsc);
		    
			ResultSet rs = preparedStatement.executeQuery();
			out.println("<html><body bgcolor='white'><table border='1'><tr><td>BANK IFSC</td><td>BANK ID</td><td>BRANCH</td>"
						 +"<td>ADDRESS</td><td>CITY</td><td>DISTRICT</td><td>STATE</td><td>BANK NAME</td></tr>");
				while(rs.next()) 
				{
					code= rs.getString("ifsc");
					 id=rs.getInt("bank_id");
					 bnch= rs.getString("branch");
					 add= rs.getString("address");
					 cty= rs.getString("city");
					 dst= rs.getString("district");
					 ste= rs.getString("state");
					 bnkn= rs.getString("bank_name");	
			    }
				out.println("<tr><td>"+code+"</td><td>"+id+"</td><td>"+bnch+"</td><td>"+add+"</td><td>"+cty+"</td><td>"+dst+"</td><td>"+ste+"</td><td>"+bnkn+"</td><td><tr>");
				out.println("</table></body></html>");		
	}
		catch(Exception e)
		{
			 e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		super.destroy();
	}

}

	
	
	
	
	
	
	
	
	
	
	