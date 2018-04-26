package com.fyle.Server_Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fyle.DbConnection_Files.DbConnector;

public class ValidateData {
	
	 public static boolean checkUser(String ifsc,String bname,String bcity) 
     {
	  String sql1 = "SELECT * FROM bank_locator.bank_branches_csv where ifsc=?";
	  String sql2 = "SELECT * FROM bank_locator.bank_branches_csv where bank_name=? AND city=?";
      boolean st =false;
      Connection con = null;
	  PreparedStatement preparedStatement = null;
	  if(ifsc!=null && ifsc!="")
	  {
             try{
            	con=DbConnector.createConnection();
     		    preparedStatement = con.prepareStatement(sql1);
     		    preparedStatement.setString(1, ifsc);
     		    ResultSet rs =preparedStatement.executeQuery();
     	        st = rs.next();
                }
             catch(Exception e)
                {
                    e.printStackTrace();
                }
	  }
	  else
	  {
		  try{
          	con=DbConnector.createConnection();
   		    preparedStatement = con.prepareStatement(sql2);
   		    preparedStatement.setString(1, bname);
   		    preparedStatement.setString(2, bcity);
   		    ResultSet rs =preparedStatement.executeQuery();
   	        st = rs.next();
              }
           catch(Exception e)
              {
                  e.printStackTrace();
              }
	  }
         return st;                 
  }   

}
