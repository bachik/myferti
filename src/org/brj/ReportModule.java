package org.brj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bjvdb.AllDBMethods;

public class ReportModule extends JFrame implements ActionListener {
	JTextField sdate,edate;
	JButton stockreport,salereport;
	JLabel sdatel,edatel;
	 JPanel panel;
	 AllDBMethods a;
	 public static String autostr;
	public ReportModule()
	 {
		  sdatel = new JLabel();
		  sdatel.setText("START DATE");
		  sdatel.setBounds(50,10,400,70);
		  sdate=new JTextField();
		  sdate.setBounds(150,25,200,30);
		  edatel = new JLabel();
		  edatel.setText("END DATE");
		  edatel.setBounds(350,10,400,70);
		  edate=new JTextField();
		  edate.setBounds(450,25,200,30);
		  stockreport=new JButton("STOCKREPORT");
		stockreport.setBounds(100, 100, 200, 50);
		salereport=new JButton("SALESREPORT");
		salereport.setBounds(300, 100, 200, 50);
		/*COTTON=new JButton("Fertilizers");
		Reports=new JButton("Fertilizers");
		COTTON.setBounds(500, 100, 200, 50);
		Reports=new JButton("Reports");
		Reports.setBounds(700, 100, 200, 50);*/
		panel=new JPanel(null);
		  panel.add(sdatel);
		  panel.add(sdate);
		  panel.add(edatel);
		  panel.add(edate);
		  panel.add(stockreport);
		  panel.add(salereport);
	      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	      setTitle("REPORT MODULE");
	      add(panel,BorderLayout.CENTER);
	      salereport.addActionListener(this);
	      stockreport.addActionListener(this);
	      //COTTON.addActionListener(this);
	      setSize(400, 200);
	      setVisible(true);
	  }
	 public void actionPerformed(ActionEvent ae)
	  {
		 int x;
		 JButton o = (JButton) ae.getSource();
	  if (o.getText().equalsIgnoreCase("STOCKREPORT")){
		  System.out.println("STOCK clicked");

	        }
	  else if(o.getText().equalsIgnoreCase("SALESREPORT"))
	  {
		  
		  System.out.println("SALES clicked");
	  }
	  /*else if(o.getText().equalsIgnoreCase("pesticides"))
	  {
		  autostr=o.getText();
		  a=new AllDBMethods();
	        x=a.insertDB("geninvoice", "customer_seq.nextval");
	       PesticidePage p=new PesticidePage();
	        p.setVisible(true);
	        p.setSize(900, 800);
	       
	  }*/
	  //NextPage page=new NextPage();
	  //page.setVisible(true);
	  //JLabel label = new JLabel("Welcome:"+value1);
	  //page.getContentPane().add(label);
	  else{
	  System.out.println("NOTHING SELECTED");
	  JOptionPane.showMessageDialog(this,"NOTHING SELECTED","Error",JOptionPane.ERROR_MESSAGE);
	  }
	}

}
