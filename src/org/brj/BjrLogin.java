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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class Login extends JFrame implements ActionListener
{
 JButton SUBMIT;
 JPanel panel;
 JLabel label1,label2,label3,label4;
 final JTextField  text1,text2;
  Login()
  {
  label1 = new JLabel();
  label1.setText("Username:");
  text1 = new JTextField(15);
  text1.setCursor(getCursor());
  text2 = new JTextField(15);
  
  label2 = new JLabel();
  label2.setText("Password:");
  label3 = new JLabel();
  label4 = new JLabel();
  SUBMIT=new JButton("SUBMIT");
  
  panel=new JPanel(new GridLayout(5,1));
  panel.add(label1);
  panel.add(text1);
  panel.add(label2);
  panel.add(text2);
  panel.add(label4);
  panel.add(SUBMIT); 
  //panel.pack();
  //panel.setLocationRelativeTo(null); 
  add(panel,BorderLayout.CENTER);
  SUBMIT.addActionListener(this);
  setTitle("BJR LOGIN");
  }
 public void actionPerformed(ActionEvent ae)
  {
  String value1=text1.getText();
  String value2=text2.getText();
  if (value1.equals("shashi") && value2.equals("shashi")) {
  Productpage page=new Productpage();
  page.setSize(900, 800);
  page.setVisible(true);
  //JLabel label = new JLabel("Welcome:"+value1);
  //page.getContentPane().add(label);
  }
  else{
  System.out.println("enter the valid username and password");
  JOptionPane.showMessageDialog(this,"Incorrect login or password",
  "Error",JOptionPane.ERROR_MESSAGE);
  }
}
}

public class BjrLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		Login frame=new Login();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,300);
        frame.setVisible(true);
}
catch(Exception e)
{
  JOptionPane.showMessageDialog(null, e.getMessage());}
}
}
