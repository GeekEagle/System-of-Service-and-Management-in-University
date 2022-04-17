package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentpunish extends JFrame{
    public Jstudentpunish() {
        this.setSize(1000,800);
        this.setTitle("学生处分信息");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel txt1 = new JLabel("输入所查学生学号：");
        JTextField txtSno = new JTextField(20);
        pane.add(txt1);
        pane.add(txtSno);

        JButton btnOK=new JButton("确定");
        pane.add(btnOK);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtSno.getText().length()==0) {
                    JOptionPane.showMessageDialog(frame, "学号必填！");
                    return;
                }
                // Create a variable for the connection string.
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

                // Declare the JDBC objects.
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    // Create and execute an SQL statement that returns some data.
                    String SQL = "select * from 处分记录表_view where StuID="+txtSno.getText();
                    stmt = con.createStatement();
                    rs =stmt.executeQuery(SQL);
                    if(rs.next())
                    {
                        Jstudentpunishupdata f = new Jstudentpunishupdata(txtSno.getText());
                        f.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "该学生无处分记录");
                        Jstudentpunishupdata f = new Jstudentpunishupdata(txtSno.getText());
                        f.setVisible(true);
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
                }
            }

        });
        JButton btnClose = new JButton("退出");
        pane.add(btnClose);
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
