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

public class SelectCourse extends JFrame {
    public SelectCourse(String teacherId) {
        this.setSize(1000,1000);
        this.setTitle("查看课表");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] listname= {"课程号","课程名","教师号","选修人数","学分","上课教室"};
        List<String[]> list = getCountFromSc(teacherId);
        String[][] listvalue = new String[list.size()][6];
        int k = 0;
        for (String[] s : list){
            listvalue[k] = s;
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
        JButton btnAdd = new JButton("查询");
        pane.add(btnAdd, BorderLayout.SOUTH);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCourse f = new SelectCourse(teacherId);
                f.setVisible(true);
            }
        });

        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public List<String[]> getCountFromSc(String teacherId){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String[]> list = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            // Create and execute an SQL statement that returns some data.
            String SQL = "select * from 课程信息表_view where TeacherID =" + teacherId;
            stmt=con.createStatement();
            rs =stmt.executeQuery(SQL);
            while(rs.next()) {
                String[] s = new String[] {rs.getString("CourseID"),rs.getString("CourseName"),rs.getString("TeacherID"),rs.getString("Counts"),rs.getString("Credit"),rs.getString("Classroom")};
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
