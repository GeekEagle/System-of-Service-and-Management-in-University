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
	        this.setTitle("ѧ����ʦ��Ϣ");
	        Container pane=this.getContentPane();
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
	        String[] listname= {"ѧ��","����","Ժϵ","�༶",
	        		"����Ա","������","��ʦ"};
	        

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
	    	            String SQL1 ="select count(*) from ѧ����ʦ��Ϣ��_view where StuID="+ID;
	    	            stmt=con.createStatement();
	    	            ResultSet rs1 =stmt.executeQuery(SQL1);
	    	            while(rs1.next()) 
	    	            {
	    	            	sum=rs1.getInt(1);
	    	            }
		    	        count=new String[sum][7];
	                    String SQL = "select * from ѧ����ʦ��Ϣ��_view where StuID="+ID;
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
			table.setFont(new Font("����",Font.PLAIN,20));
			table.setRowHeight(30);
			Dimension dim = new Dimension(300,30);
			setVisible(true);
			setAlwaysOnTop(true);
			pane.setLayout(null);
	        /*JTextField txtSno = new JTextField(20);
	        pane.add(txt1);
	        pane.add(txtSno);

	        JLabel txt2 = new JLabel("������");
	        JTextField txtName = new JTextField(20);
	        pane.add(txt2);
	        pane.add(txtName);*/
	        
	        JLabel txt3 = new JLabel("ѧԺ��");
	        String select4[] = {"��ϢѧԺ", "��ȫѧԺ","����ѧԺ","ʯ��ѧԺ","����ѧԺ","��ѧԺ"};
	        JComboBox txtpro = new JComboBox(select4);
	        txtpro.setPreferredSize(dim);
	        pane.add(txt3);
	        pane.add(txtpro);
			txt3.setBounds(20,620,150,40);txtpro.setBounds(200,620,150,40);

	        JLabel txt4 = new JLabel("�༶��");
	        JTextField txtcla = new JTextField(20);
	        pane.add(txt4);
	        pane.add(txtcla);
			txt4.setBounds(500,620,150,40);txtcla.setBounds(700,620,150,40);
	        
	        /*JLabel txt5 = new JLabel("����Ա��");
		    String select[] = {"��ϢѧԺ����Ա","����ѧԺ����Ա","ʯ��ѧԺ����Ա","����ѧԺ����Ա","��ȫѧԺ����Ա"};
		    JComboBox txtass = new JComboBox(select);
		    txtass.setPreferredSize(dim);
		    pane.add(txt5);
		    pane.add(txtass);
		    */
	        
	        JLabel txt6 = new JLabel("�����Σ�");
	        JTextField txtban = new JTextField(20);
	        pane.add(txt6);
	        pane.add(txtban);
			txt6.setBounds(20,700,150,40);txtban.setBounds(200,700,150,40);
	        
	        JLabel txt7 = new JLabel("��ʦ��û�пɲ����");
	        JTextField txtdao = new JTextField(20);
	        pane.add(txt7);
	        pane.add(txtdao);
			txt7.setBounds(500,700,150,40);txtdao.setBounds(700,700,150,40);
	        
	        JButton btnOK=new JButton("ȷ������ѧ����Ӧ��ʦ��Ϣ");
	        pane.add(btnOK);
			btnOK.setBounds(20,860,200,40);
	        JFrame frame=this;
	        btnOK.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if( txtpro.getSelectedItem().toString().length()==0||txtcla.getText().length()==0||txtban.getText().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "ѧԺ���༶������Ա�������α��");
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
	                    c=con.prepareCall("{call ѧ����ʦ(?,?,?,?,?,?)}");
	                    c.setString(1,ID);
	                    c.setString(2,txtpro.getSelectedItem().toString()+"����Ա");
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
	                	JOptionPane.showMessageDialog(frame, "����ʧ�ܣ�");
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
	         
	        
	        JButton btnClose = new JButton("�˳�");
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
	        this.setTitle("ѧ����ʦ��Ϣ");
	        Container pane=this.getContentPane();
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
			pane.setSize(1800,1500);
			String[] listname= {"ѧ��","����","Ժϵ","�༶",
	        		"����Ա","������","��ʦ"};
	        

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
	    	            String SQL1 ="select count(*) from ѧ����ʦ��Ϣ��_view";
	    	            stmt=con.createStatement();
	    	            ResultSet rs1 =stmt.executeQuery(SQL1);
	    	            while(rs1.next()) 
	    	            {
	    	            	sum=rs1.getInt(1);
	    	            }
		    	        count=new String[sum][7];
	                    String SQL = "select * from ѧ����ʦ��Ϣ��_view ";
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
			table.setFont(new Font("����",Font.PLAIN,20));
			table.setRowHeight(30);
			Dimension dim = new Dimension(300,30);
			setVisible(true);
			setAlwaysOnTop(true);
			pane.setLayout(null);
	         
	        
	        JButton btnClose = new JButton("�˳�");
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