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
        this.setTitle("�������");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTable table=new JTable();
        pane.add(table);
        JButton perapply=new JButton("������������");
        pane.add(perapply);setLayout(null);perapply.setBounds(20,20,160,40);
        JButton positive=new JButton("��Ϊ��������");
        pane.add(positive);setLayout(null);positive.setBounds(20,80,160,40);
        JButton formal=new JButton("ת��");
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
            String SQL = "{call ��������(?,?,?,?,?)}";
            CallableStatement calls = con.prepareCall(SQL);
            calls.setString(1,dat); calls.setString(2,"ѧ��");
            calls.setString(3,str[0]);calls.setString(4,"����");
            calls.setString(5,"��Ϊ��������");
            calls.execute();
            calls.close();
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("ϵͳ����");
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
            String SQL = "{call ��������(?,?,?,?,?)}";
            CallableStatement calls = con.prepareCall(SQL);
            calls.setString(1,dat); calls.setString(2,"ѧ��");
            calls.setString(3,str[0]);calls.setString(4,"����");
            calls.setString(5,"ת��");
            calls.execute();
            calls.close();
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("ϵͳ����");
        }
    }
}
