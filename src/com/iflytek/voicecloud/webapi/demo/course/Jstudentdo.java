package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentdo extends JFrame{
    public Jstudentdo() {
        this.setSize(1800,1000);
        this.setTitle("学生待办事务信息");
        Container pane=this.getContentPane();
        pane.setFont(new Font("宋体",Font.PLAIN,20));
        pane.setBounds(40,40,600,800);
        setVisible(true);
        setAlwaysOnTop(true);
        pane.setLayout(null);
        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] listname= {"操作编号","操作身份","事务类型","处理相关表名",
                "申请人学号","申请时间","是否允许","是否完成",
                "办理结果"};
        int sum=0;
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            String SQL1 = "select count(*) from 操作记录表_view ";
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
        scrollPane.setBounds(20,20,1700,700);
        table.setBounds(40,40,1800,700);
        table.setFont(new Font("宋体",Font.PLAIN,20));
        table.setRowHeight(30);
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        pane.add(scrollPane, BorderLayout.CENTER);

        JLabel txt1 = new JLabel("操作编号：");
        JTextField txtnum = new JTextField(20);
        pane.add(txt1);
        pane.add(txtnum);
        txt1.setBounds(20,850,200,40);txtnum.setBounds(300,850,200,40);

        JButton btnOK=new JButton("同意申请");
        pane.add(btnOK);
        btnOK.setBounds(800,850,200,40);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtnum.getText().length()==0) {
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
                String num = new String();
                try {
                    num = txtnum.getText();
                    System.out.println(num);
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    // Create and execute an SQL statement that returns some data.
                    c=con.prepareCall("{call 接受申请(?)}");
                    c.setString(1,num);
                    c.execute();
                    c.close();
                    frame.setVisible(false);
                    Jstudentdo f = new Jstudentdo();
                    f.setVisible(true);
                }
                // Iterate through the data in the result set and display it.
                // Handle any errors that may have occurred.
              /*  catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
                {
                	JOptionPane.showMessageDialog(frame, "修改失败！");
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

        JButton btnno=new JButton("拒绝申请");
        pane.add(btnno);
        btnno.setBounds(1100,850,200,40);
        btnno.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtnum.getText().length()==0) {
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
                    c=con.prepareCall("{call 拒绝申请(?)}");
                    c.setString(1,txtnum.getText());
                    c.execute();
                    c.close();
                    Jstudentdo f = new Jstudentdo();
                    f.setVisible(true);
                }
                // Iterate through the data in the result set and display it.
                // Handle any errors that may have occurred.
                catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
                {
                	JOptionPane.showMessageDialog(frame, "修改失败！");
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
        JButton btnRet = new JButton("返回");
        pane.add(btnRet, BorderLayout.SOUTH);
        btnRet.setBounds(1400,850,200,40);
        btnRet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
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
            String SQL = "select * from 操作记录表_view ";
            stmt=con.createStatement();
            int i=0;
            rs =stmt.executeQuery(SQL);
            while(rs.next()) {
                count[i]=new String[] {rs.getString("Alpnum"),rs.getString("Persontype"),
                        rs.getString("Aplpro"),rs.getString("Aplcon"),
                        rs.getString("Num"),rs.getString("Date"),
                        rs.getString("Limits"),rs.getString("Accomplish"),
                        rs.getString("Result")};
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
