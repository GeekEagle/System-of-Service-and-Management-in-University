package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentselect extends JFrame{
	    public Jstudentselect() {
	        this.setSize(2500,1000);
	        this.setTitle("学生信息");
	        Container pane=this.getContentPane();

	       // pane.setLayout(new FlowLayout(FlowLayout.LEFT));
	        String[] listname= {"学号","姓名","专业","年龄",
	        		"政治面貌","导师","班级号","学院",
	        		"年纪","学生状态","籍贯","志愿时长",
	        		"学费","是否缴费","绩点"};
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
	        JTable table = new JTable(listvalue,listname);
	        JScrollPane scrollPane=new JScrollPane(table);
	        scrollPane.setBounds(40,40,1800,800);
	        table.setBounds(40,40,2000,800);
	        table.setFont(new Font("宋体",Font.PLAIN,25));
	        table.setRowHeight(30);
	        scrollPane.add(table);
	        scrollPane.setViewportView(table);
	        pane.add(scrollPane, BorderLayout.CENTER);
			/*JButton btndel = new JButton("确定删除");
			btndel.setBounds(220,50,100,500);
			JFrame frame=this;
			pane.add(btndel, BorderLayout.SOUTH);
			btndel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});*/
	        JButton btnRet = new JButton("返回");
	        btnRet.setBounds(220,120,100,500);
	        JFrame frame1=this;
	        pane.add(btnRet, BorderLayout.SOUTH);
	        btnRet.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	frame1.setVisible(false);
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
	        String[][] count=new String[sum][15];
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
	            // Create and execute an SQL statement that returns some data.
	            String SQL = "select * from 学生信息表_view ";
	            stmt=con.createStatement();
	            int i=0;
	            rs =stmt.executeQuery(SQL);
	            while(rs.next()) {
	                count[i]=new String[] {rs.getString("StuID"),rs.getString("Stuname"),
	                		rs.getString("Stupro"),rs.getString("Stuage"),
	                		rs.getString("Iden"),rs.getString("Teanum"),
	                		rs.getString("Clanum"),rs.getString("College"),
	                		rs.getString("Grade"),rs.getString("Cond"),
	                		rs.getString("Location"),rs.getString("Stuvol"),
	                		rs.getString("Tuition"),rs.getString("Contui"),
	                		rs.getString("gpa")};
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