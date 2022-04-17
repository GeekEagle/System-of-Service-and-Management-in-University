package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentpunishupdata extends JFrame{
	    public Jstudentpunishupdata(String ID) {
	    	setSize(800,600);
	        setTitle("学生处分信息更新");
	        Container pane=this.getContentPane();
			pane.setSize(1800,1500);
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
	        String[] listname= {"处分编号","学号","姓名","专业",
	        		"获得处分时间","处分原因","处分处理等级"};
	        

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
	    	            String SQL1 ="select count(*) from 处分记录表_view where StuID="+ID;
	    	            stmt=con.createStatement();
	    	            ResultSet rs1 =stmt.executeQuery(SQL1);
	    	            while(rs1.next()) 
	    	            {
	    	            	sum=rs1.getInt(1);
	    	            }
		    	        count=new String[sum][7];
	                    String SQL = "select * from 处分记录表_view where StuID="+ID;
	                    stmt = con.createStatement();
	    	            int i=0;
	    	            rs =stmt.executeQuery(SQL);
	    	            while(rs.next()) {
	    	                count[i]=new String[] {rs.getString("Punum").trim(),rs.getString("StuID").trim(),
	    	                		rs.getString("Stuname").trim(),rs.getString("Stupro").trim(),
	    	                		rs.getString("Pundate").trim(),rs.getString("Punarea").trim(),
	    	                		rs.getString("Pungra").trim()};
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
	        /*JLabel txt1 = new JLabel("学号：");
	        JTextField txtSno = new JTextField(20);
	        pane.add(txt1);
	        pane.add(txtSno);*/

	        JLabel txt2 = new JLabel("姓名：");txt2.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtName = new JTextField(20);
	        pane.add(txt2);
	        pane.add(txtName);
			txt2.setBounds(20,620,150,40);txtName.setBounds(200,620,150,40);

	        JLabel txt3 = new JLabel("专业：");txt3.setFont(new Font("宋体",Font.PLAIN,20));
	        String select2[] = {"计算机", "应用化学","安全工程","自动化","石油工程","环境工程","经管专业"};
	        JComboBox txtMaj = new JComboBox(select2);
	        txtMaj.setPreferredSize(dim);
	        pane.add(txt3);
	        pane.add(txtMaj);
			txt3.setBounds(500,620,150,40);txtMaj.setBounds(700,620,150,40);

	        JLabel txt4 = new JLabel("获得时间：");txt4.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txttime = new JTextField(20);
	        pane.add(txt4);
	        pane.add(txttime);
			txt4.setBounds(20,700,150,40);txttime.setBounds(200,700,150,40);

	        JLabel txt5 = new JLabel("处分原因：");txt5.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtpname = new JTextField(20);
	        pane.add(txt5);
	        pane.add(txtpname);
	        txt5.setBounds(500,700,150,40);txtpname.setBounds(700,700,150,40);

	        
	        JLabel txt6 = new JLabel("处分处理等级：");
	        String select[] = {"开除", "留校察看","记大过","记过","严重警告","警告","通报批评"};
	        JComboBox txtplevel = new JComboBox(select);
	        txtplevel.setPreferredSize(dim);
	        pane.add(txt6);
	        pane.add(txtplevel);
			txt6.setBounds(20,780,150,40);txtplevel.setBounds(200,780,150,40);
	        
	        JLabel txt7 = new JLabel("需要修改的处分编号：");
	        JTextField txtdonum = new JTextField(20);
	        pane.add(txt7);
	        pane.add(txtdonum);
			txt7.setBounds(500,780,150,40);txtdonum.setBounds(700,780,150,40);
	        
	        JButton btnOK=new JButton("确定插入学生处分信息");
	        pane.add(btnOK);
			btnOK.setBounds(20,860,200,40);
	        JFrame frame=this;
	        btnOK.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if(txtName.getText().length()==0 || txtMaj.getSelectedItem().toString().length()==0
	                        ||txttime.getText().length()==0||txtpname.getText().length()==0||txtplevel.getSelectedItem().toString().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "学号，姓名，专业，获得处分时间，处分原因，处分处理等级必填！");
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
	                    c=con.prepareCall("{call 处分插入(?,?,?,?,?,?)}");
	                    c.setString(1,ID);
	                    c.setString(2,txtName.getText());
	                    c.setString(3,txtMaj.getSelectedItem().toString());
	                    c.setString(4,txttime.getText());
	                    c.setString(5,txtpname.getText());
	                    c.setString(6,txtplevel.getSelectedItem().toString());
	                    c.execute();
	                    c.close();
	                    frame.setVisible(false);
	                    Jstudentpunishupdata f = new Jstudentpunishupdata(ID);
	    		        f.setVisible(true);
	                }
	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	               /* catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
	                {
	                	JOptionPane.showMessageDialog(frame, "插入失败！");
	                }*/
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
	         
	        
	        JButton btnup=new JButton("确定更新学生处分信息");
	        pane.add(btnup);
			btnup.setBounds(500,860,200,40);
	        btnup.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if(txtdonum.getText().length()==0||txtName.getText().length()==0 || txtMaj.getSelectedItem().toString().length()==0
	                        ||txttime.getText().length()==0||txtpname.getText().length()==0||txtplevel.getSelectedItem().toString().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "操作编号，学号，姓名，专业，获得处分时间，处分原因，处分处理等级必填！");
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
	                    c=con.prepareCall("{call 处分更新(?,?,?,?,?,?,?)}");
	                    c.setString(1,txtdonum.getText());
	                    c.setString(2,ID);
	                    c.setString(3,txtName.getText());
	                    c.setString(4,txtMaj.getSelectedItem().toString());
	                    c.setString(5,txttime.getText());
	                    c.setString(6,txtpname.getText());
	                    c.setString(7,txtplevel.getSelectedItem().toString());
	                    c.execute();
	                    c.close();
	                    frame.setVisible(false);
	                    Jstudentpunishupdata f = new Jstudentpunishupdata(ID);
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
	        
	        JButton btndel=new JButton("确定删除学生处分信息");
	        pane.add(btndel);
			btndel.setBounds(20,940,200,40);
	        btndel.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if(txtdonum.getText().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "操作编号必填！");
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
	                    c=con.prepareCall("{call 处分删除(?)}");
	                    c.setString(1,txtdonum.getText());
	                    c.execute();
	                    c.close();
	                    frame.setVisible(false);
	                    Jstudentpunishupdata f = new Jstudentpunishupdata(ID);
	    		        f.setVisible(true);
	                }
	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	                catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
	                {
	                	JOptionPane.showMessageDialog(frame, "删除失败！");
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
			btnClose.setBounds(500,940,200,40);
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