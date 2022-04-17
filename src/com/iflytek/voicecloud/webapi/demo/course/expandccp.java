package com.iflytek.voicecloud.webapi.demo.course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class expandccp extends JFrame {
    public expandccp(String[] str){
        this.setSize(400,200);
        this.setTitle("党务相关");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTable table=new JTable();
        pane.add(table);
        JButton perapply=new JButton("党务事项申请");
        pane.add(perapply);setLayout(null);perapply.setBounds(20,20,160,40);
        JButton positive=new JButton("成为积极分子");
        pane.add(positive);setLayout(null);positive.setBounds(20,80,160,40);
        JButton formal=new JButton("转正");
        pane.add(formal);setLayout(null);formal.setBounds(220,80,160,40);
        setVisible(true);positive.setVisible(false);formal.setVisible(false);
        perapply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                positive.setVisible(true);formal.setVisible(true);
                positive.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jijifenziapl(str);
                    }
                });
                formal.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zhuanzhengapl(str);
                    }
                });
            }
        });
    }
    public void jijifenziapl(String[] str){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dat = format.format(date);
            String SQL = "{call 发起申请(?,?,?,?,?)}";
            CallableStatement calls = con.prepareCall(SQL);
            calls.setString(1,dat); calls.setString(2,"学生");
            calls.setString(3,str[0]);calls.setString(4,"党务");
            calls.setString(5,"成为积极分子");
            calls.execute();
            calls.close();
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("系统故障");
        }
    }
    public void zhuanzhengapl(String[] str){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dat = format.format(date);
            String SQL = "{call 发起申请(?,?,?,?,?)}";
            CallableStatement calls = con.prepareCall(SQL);
            calls.setString(1,dat); calls.setString(2,"学生");
            calls.setString(3,str[0]);calls.setString(4,"党务");
            calls.setString(5,"转正");
            calls.execute();
            calls.close();
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("系统故障");
        }
    }
}
