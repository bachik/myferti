package org.bjvdb;
import java.sql.*;

import javax.swing.JOptionPane;
public class AllDBMethods {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	public AllDBMethods()
	{
		try
		{
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@:1521:orcl","shashi","root");
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
		catch(Exception e)
        {
        	 JOptionPane.showMessageDialog(null, e.getMessage());
        }
	}
	public int insertDB(String tblname,String insertvalues)
	{
		int x=0;
		try
		{
	     x=stmt.executeUpdate("insert into "+tblname+" values("+insertvalues+")");
		}
		catch(Exception e)
        {
        	 JOptionPane.showMessageDialog(null, e.getMessage());
        }
		return x;
		
	}
	public ResultSet retrieveDB(String tblname,String attributes)
	{
		try{
		rs=stmt.executeQuery("select "+attributes+" from "+tblname);
		}
		catch(Exception e)
		{
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return rs;
	}
public static void main(String ar[])
{
	AllDBMethods a=new AllDBMethods();
	//int x=insertDB("geninvoice","customer_seq.nextval");
	
}
}
