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


public class CountFrame extends JFrame {
    //private JScrollPane scrocllPane;

    public CountFrame(String courseId) {
        this.setSize(1000,1000);
        this.setTitle("�ɼ�ͳ��");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] listname= {"ѧ��ѧ��","�γ̺�","�ɼ�","ѧ��"};
        List<String[]> list = getCountFromSc(courseId);
        for(String[] s : list){
            for(String ss : s){
                System.out.print(ss + " ");
            }
            System.out.println();
        }
        String[][] listvalue = new String[list.size()][4];
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
                CountFrame f = new CountFrame(courseId);
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
            String SQL = "select * from ѡ����Ϣ��_view where CourseID =" + "'"+courseId +"'"+ " order by Grade desc";
            System.out.println(SQL);
            stmt=con.createStatement();
            rs =stmt.executeQuery(SQL);
            while(rs.next()) {
                String[] s = new String[] {rs.getString("StuID"),rs.getString("CourseID"),rs.getString("Grade"),rs.getString("Credit")};
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