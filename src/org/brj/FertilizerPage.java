package org.brj;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.bjvdb.AllDBMethods;

public class FertilizerPage extends JFrame implements  ActionListener {
	 ArrayList<String> ar=new ArrayList<String>();
	   JTextComponent textComponent;
	 JButton Cost,SUBMIT,Save,ecustomer,ncustomer,genInvoice;
	 JPanel panel;
	 JLabel label1,label2,label3,label4,label5,label6,invoice,title;
	 final JTextField  text1,text2,text4,text5,billno;
	 private Connection con;
	    private Statement stmt;
	    private ResultSet rs;
	    Autoexntender a;
	    AllDBMethods adb;
	 public FertilizerPage()
	 {
		 try
	        {
	        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shashi","root");
	        stmt=con.createStatement();
	        }
	        catch(Exception e)
	        {
	        	 JOptionPane.showMessageDialog(null, e.getMessage());
	        }
		 adb=new AllDBMethods();
		 a=new Autoexntender();
		 title = new JLabel();
		  title.setText("WELCOME TO BILLING PAGE");
		  title.setBounds(400,25,400,70);
		 invoice = new JLabel();
		  invoice.setText("INVOICE NUMBER:");
		  invoice.setBounds(100,90,400,70);
		  billno=new JTextField();
		  billno.setBounds(400,100,150,30);
		  label5 = new JLabel();
		  label5.setText("CUSTOMER NAME:");
		  label5.setBounds(100,140,400,70);
		  text4 = new JTextField(15);
		  text4.setBounds(400,150,150,30);
		  label6= new JLabel();
		  label6.setText("TOWN:");
		  label6.setBounds(100, 190,400,70);
		  text5 = new JTextField(15);
		  text5.setBounds(400, 200,150,30);
		  label1 = new JLabel();
		  label1.setText("PRODUCT NAME:");
		  label1.setBounds(100,240,400,70);
		  a.setBounds(400, 250,150, 50);
		  Cost=new JButton("SHOW COST");
		  Cost.setBounds(100,300,150,30);
		  text1 = new JTextField(15);
		  text1.setBounds(400,300,150,30);
		  label3 = new JLabel();
		  label3.setText("QUANTITY:");
		  label3.setBounds(100,340,400,70);
		  text2 = new JTextField(15);
		  text2.setBounds(400,350,150,30);
		  SUBMIT=new JButton("ADDPRODUCT");
		  SUBMIT.setBounds(100,500,150,30);
		  Save=new JButton("GenarateBill");
		  Save.setBounds(400,500,150,30);
		  panel=new JPanel(null);
		  panel.add(title);
		  panel.add(invoice);
		  panel.add(billno);
		  panel.add(label5);
		  panel.add(text4);
		  panel.add(label6);
		  panel.add(text5);
		  panel.add(label1);
		  panel.add(a);
		  panel.add(Cost);
		  //panel.add(label2);
		  panel.add(text1);
		  panel.add(label3);
		  panel.add(text2);
		  panel.add(SUBMIT); 
		  panel.add(Save); 
		  ResultSet rs2=adb.retrieveDB("geninvoice", "count(*)");
			 try{	 
			 rs2.next();
			 int x=rs2.getInt(1);
			 System.out.println(x);
			 ResultSet rs=adb.retrieveDB("geninvoice","invoicenumber");
			 rs.absolute(x);
			 //System.out.println(rs.getString("invoicenumber"));
			 billno.setText(rs.getString("invoicenumber"));
			 //billno.setText(rs.getInt("invoicenumber"));
			  //rs.absolute(2);
			  
			  }
			  catch(Exception e)
			  {
				  
			  }
		  add(panel,BorderLayout.CENTER);
		  Cost.addActionListener(this);
		  SUBMIT.addActionListener(this);
		  Save.addActionListener(this);
		 
	 }
	 
	@Override
	
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		JButton o = (JButton)ae.getSource();
		  if (o.getText().equalsIgnoreCase("SHOW COST")){
			  try{
				  String str;
				  str=a.tf.getText();
				  System.out.println();
			        rs=stmt.executeQuery("select price from fertilizers where fertname='"+str+"'");
			          while(rs.next()){
			        	  text1.setText(rs.getString("price"));
			          }
			          	
			          	  
			  }
			        catch(Exception e)
			        {
			        	 JOptionPane.showMessageDialog(null, e.getMessage());
			        }
		  }else if (o.getText().equalsIgnoreCase("ADDPRODUCT")){
			  String str=a.tf.getText();
			  ar.add(str);  
			  
			  JOptionPane.showMessageDialog(null, "product suessfully added");
			  a.tf.setText("");
			  text1.setText("");
			  text2.setText("");
		  }
		  else if (o.getText().equalsIgnoreCase("GenarateBill")){
			  int sum=0;
			  ActualReportFrame.showViewer();
	           for(String x:ar)
	           {
	        	   try{
	        		   
	        	        rs=stmt.executeQuery("select price from Fertilizers where fertname='"+x+"'");
	        	          while(rs.next()){
	        	             int price=Integer.parseInt(rs.getString("price")); 
	        	             JOptionPane.showMessageDialog(null, "The"+x+"price is"+ price);
	        	             sum=sum+price;
	        	             
	        	          }
	        	          
	        	        }
	        	        catch(Exception e)
	        	        {
	        	        	 JOptionPane.showMessageDialog(null, e.getMessage());
	        	        }
	        	   JOptionPane.showMessageDialog(null,sum);
	        	   //try {
	        		   
						//boolean complete = textComponent.print();
	        		    //if (complete) {
	        		        /* show a success message  */
	        		      // System.out.println("complted");
	        		    //} else {
	        		        /*show a message indicating that printing was cancelled */
	        		       
	        		    //}
	        		//} catch (Exception pe) {
	        		    /* Printing failed, report to the user */
	        		   
	        		}
	           //}
	  //}
		
	}
	}

}
