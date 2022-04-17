package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentsign extends JFrame{
	    public Jstudentsign() {
	        this.setSize(1000,800);
	        this.setTitle("学生注册签到信息");
	        Container pane=this.getContentPane();
	        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
	        String[] listname= {"学号","姓名","学生状态","是否缴费",
	        		};
	        int sum=0;
	        try {
		        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            Connection con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	            String SQL1 = "select count(*) from 学生信息表_view ";
	            Statement stmt=con.createStatement();;
	            ResultSet rs1 =stmt.executeQuery(SQL1);
	            while(rs1.next()) 
	            {
	            	sum=rs1.getInt(1);
	            }
	        }
	        catch (Exception ex) {
	            ex.printStackTrace();
	        } 
	        String[][] listvalue= getCountFromSc(sum);

	        for(int i=0;i<listvalue.length;i++) {
	            for (int j=0;j<listvalue[i].length;j++) {
	                System.out.print(listvalue[i][j]+" ");
	            }
	            System.out.println();
	        }
	        JTable table =new JTable(listvalue,listname);
	        JScrollPane scrollPane=new JScrollPane(table);
	        scrollPane.add(table);
	        scrollPane.setViewportView(table);
	        pane.add(scrollPane, BorderLayout.CENTER);
	        JButton btnRet = new JButton("返回");
	        pane.add(btnRet, BorderLayout.SOUTH);
	        btnRet.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JstudentFrame f = new JstudentFrame();
	                f.setVisible(true);
	            }
	        });

	        this.setLocationRelativeTo(null);
	        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    public String[][] getCountFromSc(int sum){
	        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        String[][] count=new String[sum][4];
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	            // Create and execute an SQL statement that returns some data.
	            String SQL = "select  StuID,Stuname,Tuition,Contui from 学生信息表_view ";
	            stmt=con.createStatement();
	            int i=0;
	            rs =stmt.executeQuery(SQL);
	            while(rs.next()) {
	                count[i]=new String[] {rs.getString("StuID"),rs.getString("Stuname"),
	                		rs.getString("Tuition"),rs.getString("Contui"),
	                		};
	                i++;
	            }
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
			return count;
	    }
	}