package org.brj;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.bjvdb.AllDBMethods;

public class Productpage extends  JFrame implements ActionListener{

	/**
	 * @param args
	 */
	JButton SEEDS,PESTICIDES,COTTON,Reports;
	JLabel title;
	 JPanel panel;
	 AllDBMethods a;
	 public static String autostr;
	Productpage()
	 {
		title=new JLabel();
		title.setText("WELCOME TO SELECTION OF DIFFERENT MODULES");
		title.setBounds(350, 30, 400, 50);
		SEEDS=new JButton("SEEDS");
		SEEDS.setBounds(50, 100, 200, 50);
		PESTICIDES=new JButton("PESTICIDES");
		PESTICIDES.setBounds(250, 100, 200, 50);
		COTTON=new JButton("Fertilizers");
		Reports=new JButton("reports");
		COTTON.setBounds(450, 100, 200, 50);
		Reports=new JButton("Reports");
		Reports.setBounds(650, 100, 200, 50);
		panel=new JPanel(null);
		panel.add(title);
		  panel.add(SEEDS);
		  panel.add(PESTICIDES);
		  panel.add(COTTON);
		  panel.add(Reports);
	     setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	      setTitle("PRODUCT TYPE SELECTION");
	      add(panel,BorderLayout.CENTER);
	      SEEDS.addActionListener(this);
	      PESTICIDES.addActionListener(this);
	      COTTON.addActionListener(this);
	      Reports.addActionListener(this);
	      setSize(400, 200);
	      setVisible(true);
	  }
	 public void actionPerformed(ActionEvent ae)
	  {
		 int x;
		 JButton o = (JButton) ae.getSource();
	  if (o.getText().equalsIgnoreCase("seeds")){
		   autostr=o.getText();
		    a=new AllDBMethods();
	        x=a.insertDB("geninvoice", "customer_seq.nextval");
	        System.out.print("seeds hello");
		    Seedspage s=new Seedspage();
	        s.setVisible(true);
	        s.setSize(900, 800);
	        }
	  else if(o.getText().equalsIgnoreCase("Fertilizers"))
	  {
		  autostr=o.getText();
		  System.out.println("cotton clicked");
		  a=new AllDBMethods();
		  x=a.insertDB("geninvoice", "customer_seq.nextval");
	        System.out.print("fertilizers hello");
		  FertilizerPage p=new FertilizerPage();
	        p.setVisible(true);
	        p.setSize(900, 800);
	        
	        
	  }
	  else if(o.getText().equalsIgnoreCase("pesticides"))
	  {
		  autostr=o.getText();
		  a=new AllDBMethods();
	        x=a.insertDB("geninvoice", "customer_seq.nextval");
	       PesticidePage p=new PesticidePage();
	        p.setVisible(true);
	        p.setSize(900, 800);
	       
	  }
	  else if(o.getText().equalsIgnoreCase("reports"))
	  {
		  System.out.println("reports");
	       ReportModule p=new ReportModule();
	        p.setVisible(true);
	        p.setSize(900, 800);
	       
	  }
	  //NextPage page=new NextPage();
	  //page.setVisible(true);
	  //JLabel label = new JLabel("Welcome:"+value1);
	  //page.getContentPane().add(label);
	  else{
	  System.out.println("NOTHING SELECTED");
	  JOptionPane.showMessageDialog(this,"NOTHING SELECTED",
	  "Error",JOptionPane.ERROR_MESSAGE);
	  }
	}
}
