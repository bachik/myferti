package org.brj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bjvdb.AllDBMethods;

public class Autoexntender extends JPanel {
	public final JTextField tf;
    public final JComboBox combo = new JComboBox();
    private final Vector<String> v = new Vector<String>();
    private ResultSet rs;
    public Autoexntender() {
        super(new BorderLayout());
        combo.setEditable(true);
        tf = (JTextField) combo.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
               EventQueue.invokeLater(new Runnable() {
            public void run() {
                String text = tf.getText();
                        if(text.length()==0) {
                    combo.hidePopup();
                    setModel(new DefaultComboBoxModel(v), "");
                }else{
                    DefaultComboBoxModel m = getSuggestedModel(v, text);
                    if(m.getSize()==0 || hide_flag) {
                          combo.hidePopup();
                        hide_flag = false;
                    }else{
                    	
                        setModel(m, text);
                        combo.showPopup();
                    }
                }
            }
        });
            }
                public void keyPressed(KeyEvent e) {
               String text = tf.getText();
         int code = e.getKeyCode();
             if(code==KeyEvent.VK_ENTER) {
            if(!v.contains(text)) {
                v.addElement(text);
                Collections.sort(v);
                setModel(getSuggestedModel(v, text), text);
            }
            hide_flag = true; 
        }else if(code==KeyEvent.VK_ESCAPE) {
            hide_flag = true; 
        }else if(code==KeyEvent.VK_RIGHT) {
            for(int i=0;i<v.size();i++) {
                String str = v.elementAt(i);
                if(str.startsWith(text)) {
                    combo.setSelectedIndex(-1);
                   tf.setText(str);
                    return;
                }
            }
        }
            }
      });
        
        if(Productpage.autostr.equalsIgnoreCase("seeds"))
        {
        try{
        	AllDBMethods a=new AllDBMethods();
        	rs=a.retrieveDB("seeds", "*");
        //rs=stmt.executeQuery("select * from seeds");
          while(rs.next()){
                  v.addElement(rs.getString(1));
          }
        }
        catch(Exception e)
        {
        	 JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
        if(Productpage.autostr.equalsIgnoreCase("fertilizers"))
        {
        try{
        	AllDBMethods a=new AllDBMethods();
        	rs=a.retrieveDB("fertilizers", "*");
        //rs=stmt.executeQuery("select * from fertilizers");
          while(rs.next()){
                  v.addElement(rs.getString(1));
          }
        }
        catch(Exception e)
        {
        	 JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
        if(Productpage.autostr.equalsIgnoreCase("pesticides"))
        {
        try{
        	AllDBMethods a=new AllDBMethods();
        	rs=a.retrieveDB("pesticides", "*");
        //rs=stmt.executeQuery("select * from fertilizers");
          while(rs.next()){
                  v.addElement(rs.getString(1));
          }
        }
        catch(Exception e)
        {
        	 JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
       
        setModel(new DefaultComboBoxModel(v), "");
        JPanel p = new JPanel(new BorderLayout());
        //p.setBorder(BorderFactory.createTitledBorder("A"));
        p.add(combo, BorderLayout.NORTH);
        add(p);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(300, 150));
    }
    private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
    	   
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str);
    }
private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(String s: list) {
            if(s.startsWith(text)) m.addElement(s);
        }
        return m;
    }
    }
