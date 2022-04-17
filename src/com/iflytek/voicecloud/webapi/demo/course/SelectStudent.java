package com.iflytek.voicecloud.webapi.demo.course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SelectStudent extends JFrame {
    //private JScrollPane scrocllPane;

    public SelectStudent(String courseId) {
        this.setSize(1000,1000);
        this.setTitle("ѡ��ѧ������");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] listname= {"ѧ��ѧ��","ѧ������","ѧԺ","�꼶","�༶��"};
        List<String[]> list = getCountFromSc(courseId);
        String[][] listvalue = new String[list.size()][5];
        int k = 0;
        for (String[] s : list){
            listvalue[k] = s;
            k++;
        }

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
        JButton btnAdd = new JButton("��ѯ");
        pane.add(btnAdd, BorderLayout.SOUTH);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectStudent f = new SelectStudent(courseId);
                f.setVisible(true);
            }
        });

        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public List<String[]> getCountFromSc(String courseId){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String[]> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            // Create and execute an SQL statement that returns some data.
            String SQL = "select ѧ����Ϣ��_view.StuID sno,ѧ����Ϣ��_view.StuName sname,ѧ����Ϣ��_view.College coll,ѧ����Ϣ��_view.Grade gra,ѧ����Ϣ��_view.Clanum cla from ѡ����Ϣ��_view,ѧ����Ϣ��_view where ѡ����Ϣ��_view.StuID = ѧ����Ϣ��_view.StuID and CourseID = " + "'"+courseId +"'";
            stmt=con.createStatement();
            rs =stmt.executeQuery(SQL);
            while(rs.next()) {
                String[] s = new String[] {rs.getString("sno"),rs.getString("sname"),rs.getString("coll"),rs.getString("gra"),rs.getString("cla")};
                //count[i]=new String[] {rs.getString("sno"),rs.getString("cno"),rs.getString("grade"),rs.getString("credit")};
                list.add(s);
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
        return list;
    }
}