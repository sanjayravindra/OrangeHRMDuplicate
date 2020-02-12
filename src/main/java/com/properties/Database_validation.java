package com.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.testng.asserts.SoftAssert;

public class Database_validation extends Property
{
	static Connection con = null;
    private static Statement stmt=null;
    public static String DB_URL = "jdbc:mysql://localhost:3306/orangehrm_mysql";   
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "root";
    public static String query = "select  employee_id,emp_firstname,emp_lastname,emp_birthday,emp_gender,emp_marital_status,nation_code from hs_hr_employee order by employee_id asc ;";
    public static String rowcount="SELECT COUNT(*) AS rowcount FROM hs_hr_employee;";
    public static int rownum;
    public static int colnum;
    SoftAssert s = new SoftAssert();
    
    
    
    
    public void test(String sheetname) throws Throwable
    {
    	String dbClass = "com.mysql.cj.jdbc.Driver";

        Class.forName(dbClass).newInstance();

        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


        
        
        List<String> data = new ArrayList<String>();
        stmt = con.createStatement();
        ResultSet rs1 = stmt.executeQuery(query);
       ResultSetMetaData rsmd = rs1.getMetaData();
      colnum= rsmd.getColumnCount();
      while(rs1.next())
      {
    	  
    	  for (int i = 1; i <=colnum; i++) 
    	  {
			data.add(rs1.getString(i));
		}
      }

    ResultSet rs2 = stmt.executeQuery(rowcount);
    while(rs2.next())
    {
    	rownum = rs2.getInt("rowcount");

    }

       for(int m = 7; m<rownum*colnum;m++)
       {

    	   for(int i=1;i<rownum;i++)
    	   {  
    		   String result = data.get(m)+data.get(m+1)+data.get(m+2)+data.get(m+3)+data.get(m+4)+data.get(m+5)+data.get(m+6);

    	
    	String excel =super.getcelldata(sheetname, "Emp_ID", i)+super.getcelldata(sheetname, "Emp_firstname", i)+super.getcelldata(sheetname, "Emp_lastname", i)+
    			super.getcelldata(sheetname, "DOB", i)+super.getcelldata(sheetname, "Gender code", i)+
    			super.getcelldata(sheetname, "Marital Status", i)+super.getcelldata(sheetname, "Nation code", i);

    	
    		   s.assertEquals(super.getcelldata(sheetname, "Emp_ID", i), data.get(m));

    		   s.assertEquals(super.getcelldata(sheetname, "Emp_firstname", i), data.get(m+1));
 
    	
    		   s.assertEquals(super.getcelldata(sheetname, "Emp_lastname", i), data.get(m+2));
   
  
    	
    		   s.assertEquals(super.getcelldata(sheetname, "DOB", i), data.get(m+3));
    		 
  
    	
    		   s.assertEquals(super.getcelldata(sheetname, "Gender code", i), data.get(m+4));
    		 

  
    		   s.assertEquals(super.getcelldata(sheetname, "Marital Status", i), data.get(m+5));
    		
  
    
    		   s.assertEquals(super.getcelldata(sheetname, "Nation code", i), data.get(m+6));
    		  
    		   if(result.equalsIgnoreCase(excel))
    	    	{
    			
    			   super.writeExcel(sheetname, "DB Validation", i, "Pass");
    	    	}
    		   else
    		   {
    			
    			   super.writeExcel(sheetname, "DB Validation", i, "Fail");
    		   }
    		   
    		   ++m;++m;++m;++m;++m;++m;++m;
    	} 
    	   }
    	   
       
       if (con != null) 
       {

           con.close();

           }
       s.assertAll();
    }
    
}
