package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class JstudentteacherupdateFrame extends JFrame{
	    public JstudentteacherupdateFrame(String ID) {
	    	this.setSize(1000,600);
	        this.setTitle("学生老师信息");
	        Container pane=this.getContentPane();
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
	        String[] listname= {"学号","姓名","院系","班级",
	        		"辅导员","班主任","导师"};
	        

	        // Create a variable for the connection string.
	        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

	                // Declare the JDBC objects.
	                Connection con = null;
	                Statement stmt = null;
	                ResultSet rs = null;
	                int sum=0;
	                String[][] count = null;
	                try {
	                    // Establish the connection.
	                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
	                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	                    // Create and execute an SQL statement that returns some data.
	    	            String SQL1 ="select count(*) from 学生老师信息表_view where StuID="+ID;
	    	            stmt=con.createStatement();
	    	            ResultSet rs1 =stmt.executeQuery(SQL1);
	    	            while(rs1.next()) 
	    	            {
	    	            	sum=rs1.getInt(1);
	    	            }
		    	        count=new String[sum][7];
	                    String SQL = "select * from 学生老师信息表_view where StuID="+ID;
	                    stmt = con.createStatement();
	    	            int i=0;
	    	            rs =stmt.executeQuery(SQL);
	    	            while(rs.next()) {
	    	                count[i]=new String[] {rs.getString("StuID"),rs.getString("Stuname"),
	    	                		rs.getString("College"),rs.getString("Clanum"),	       
	    	                		rs.getString("Assistname"),rs.getString("ClassTeaname"),
	    	                		rs.getString("Turtorname")};
	    	                i++;
	    	            }
	                }
	    	           
	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	                catch (Exception ex) {
	                    ex.printStackTrace();
	                } finally {
	                    if (rs != null)
	                        try {
	                            rs.close();
	                        } catch (Exception e1) {
	                        }
	                    if (stmt != null)
	                        try {
	                            stmt.close();
	                        } catch (Exception e2) {
	                        }
	                    if (con != null)
	                        try {
	                            con.close();
	                        } catch (Exception e3) {
	                        }
	                };
	        JTable table =new JTable(count,listname);
	        JScrollPane scrollPane=new JScrollPane(table);
	        scrollPane.add(table);
	        scrollPane.setViewportView(table);
	        pane.add(scrollPane, BorderLayout.CENTER);
			scrollPane.setBounds(40,40,800,600);
			table.setBounds(40,40,800,600);
			table.setFont(new Font("宋体",Font.PLAIN,20));
			table.setRowHeight(30);
			Dimension dim = new Dimension(300,30);
			setVisible(true);
			setAlwaysOnTop(true);
			pane.setLayout(null);
	        /*JTextField txtSno = new JTextField(20);
	        pane.add(txt1);
	        pane.add(txtSno);

	        JLabel txt2 = new JLabel("姓名：");
	        JTextField txtName = new JTextField(20);
	        pane.add(txt2);
	        pane.add(txtName);*/
	        
	        JLabel txt3 = new JLabel("学院：");
	        String select4[] = {"信息学院", "安全学院","化工学院","石工学院","经管学院","理学院"};
	        JComboBox txtpro = new JComboBox(select4);
	        txtpro.setPreferredSize(dim);
	        pane.add(txt3);
	        pane.add(txtpro);
			txt3.setBounds(20,620,150,40);txtpro.setBounds(200,620,150,40);

	        JLabel txt4 = new JLabel("班级：");
	        JTextField txtcla = new JTextField(20);
	        pane.add(txt4);
	        pane.add(txtcla);
			txt4.setBounds(500,620,150,40);txtcla.setBounds(700,620,150,40);
	        
	        /*JLabel txt5 = new JLabel("辅导员：");
		    String select[] = {"信息学院辅导员","化工学院辅导员","石工学院辅导员","经管学院辅导员","安全学院辅导员"};
		    JComboBox txtass = new JComboBox(select);
		    txtass.setPreferredSize(dim);
		    pane.add(txt5);
		    pane.add(txtass);
		    */
	        
	        JLabel txt6 = new JLabel("班主任：");
	        JTextField txtban = new JTextField(20);
	        pane.add(txt6);
	        pane.add(txtban);
			txt6.setBounds(20,700,150,40);txtban.setBounds(200,700,150,40);
	        
	        JLabel txt7 = new JLabel("导师（没有可不填）：");
	        JTextField txtdao = new JTextField(20);
	        pane.add(txt7);
	        pane.add(txtdao);
			txt7.setBounds(500,700,150,40);txtdao.setBounds(700,700,150,40);
	        
	        JButton btnOK=new JButton("确定更新学生对应老师信息");
	        pane.add(btnOK);
			btnOK.setBounds(20,860,200,40);
	        JFrame frame=this;
	        btnOK.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if( txtpro.getSelectedItem().toString().length()==0||txtcla.getText().length()==0||txtban.getText().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "学院，班级，辅导员，班主任必填！");
	                    return;
	                }
	                // Create a variable for the connection string.
	                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

	                // Declare the JDBC objects.
	                Connection con = null;
	                Statement stmt = null;
	                ResultSet rs = null;
	                CallableStatement c;
	                try {
	                    // Establish the connection.
	                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
	                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	                    // Create and execute an SQL statement that returns some data.
	                    c=con.prepareCall("{call 学生老师(?,?,?,?,?,?)}");
	                    c.setString(1,ID);
	                    c.setString(2,txtpro.getSelectedItem().toString()+"辅导员");
	                    c.setString(3,txtdao.getText());
	                    c.setString(4,txtban.getText());
	                    c.setString(5,txtcla.getText());
	                    c.setString(6,txtpro.getSelectedItem().toString());
	                    c.execute();
	                    c.close();
	                    frame.setVisible(false);
	                    JstudentteacherupdateFrame f = new JstudentteacherupdateFrame(ID);
	    		        f.setVisible(true);
	                }
	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	                catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
	                {
	                	JOptionPane.showMessageDialog(frame, "更新失败！");
	                }
	                catch (Exception ex) {
	                    ex.printStackTrace();
	                } finally {
	                    if (rs != null)
	                        try {
	                            rs.close();
	                        } catch (Exception e1) {
	                        }
	                    if (stmt != null)
	                        try {
	                            stmt.close();
	                        } catch (Exception e2) {
	                        }
	                    if (con != null)
	                        try {
	                            con.close();
	                        } catch (Exception e3) {
	                        }
	                }
	            }

	        });
	         
	        
	        JButton btnClose = new JButton("退出");
	        pane.add(btnClose);
			btnClose.setBounds(500,860,200,40);
	        btnClose.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                frame.setVisible(false);
	            }

	        });
	        this.setLocationRelativeTo(null);
	        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    
	    public JstudentteacherupdateFrame() {
	    	this.setSize(1800,800);
	        this.setTitle("学生老师信息");
	        Container pane=this.getContentPane();
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
			pane.setSize(1800,1500);
			String[] listname= {"学号","姓名","院系","班级",
	        		"辅导员","班主任","导师"};
	        

	        // Create a variable for the connection string.
	        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

	                // Declare the JDBC objects.
	                Connection con = null;
	                Statement stmt = null;
	                ResultSet rs = null;
	                int sum=0;
	                String[][] count = null;
	                try {
	                    // Establish the connection.
	                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
	                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	                    // Create and execute an SQL statement that returns some data.
	    	            String SQL1 ="select count(*) from 学生老师信息表_view";
	    	            stmt=con.createStatement();
	    	            ResultSet rs1 =stmt.executeQuery(SQL1);
	    	            while(rs1.next()) 
	    	            {
	    	            	sum=rs1.getInt(1);
	    	            }
		    	        count=new String[sum][7];
	                    String SQL = "select * from 学生老师信息表_view ";
	                    stmt = con.createStatement();
	    	            int i=0;
	    	            rs =stmt.executeQuery(SQL);
	    	            while(rs.next()) {
	    	                count[i]=new String[] {rs.getString("StuID"),rs.getString("Stuname"),
	    	                		rs.getString("College"),rs.getString("Clanum"),	       
	    	                		rs.getString("Assistname"),rs.getString("ClassTeaname"),
	    	                		rs.getString("Turtorname")};
	    	                i++;
	    	            }
	                }
	    	           
	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	                catch (Exception ex) {
	                    ex.printStackTrace();
	                } finally {
	                    if (rs != null)
	                        try {
	                            rs.close();
	                        } catch (Exception e1) {
	                        }
	                    if (stmt != null)
	                        try {
	                            stmt.close();
	                        } catch (Exception e2) {
	                        }
	                    if (con != null)
	                        try {
	                            con.close();
	                        } catch (Exception e3) {
	                        }
	                };
	        JTable table =new JTable(count,listname);
	        JScrollPane scrollPane=new JScrollPane(table);
	        scrollPane.add(table);
	        scrollPane.setViewportView(table);
	        pane.add(scrollPane, BorderLayout.CENTER);
			scrollPane.setBounds(40,40,800,600);
			table.setBounds(40,40,800,600);
			table.setFont(new Font("宋体",Font.PLAIN,20));
			table.setRowHeight(30);
			Dimension dim = new Dimension(300,30);
			setVisible(true);
			setAlwaysOnTop(true);
			pane.setLayout(null);
	         
	        
	        JButton btnClose = new JButton("退出");
	        pane.add(btnClose);
			btnClose.setBounds(500,940,800,40);
	        JFrame frame=this;
	        btnClose.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                frame.setVisible(false);
	            }

	        });
	        this.setLocationRelativeTo(null);
	        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
}