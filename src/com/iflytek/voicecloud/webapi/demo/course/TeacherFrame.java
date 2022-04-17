package com.iflytek.voicecloud.webapi.demo.course;

import com.iflytek.voicecloud.webapi.demo.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class TeacherFrame extends JFrame{
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String courseId = null;
    String credit = null;
    public  TeacherFrame(String teacherId) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        try {
            // Establish the connection.
            //com.microsoft.jdbc.Sqlserver.SQLServerDriver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            String SQL = "select CourseID,Credit from 课程信息表_view where TeacherID = " + teacherId;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseId = rs.getString("CourseID");
                credit = rs.getString("Credit");
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setSize(300,300);
        this.setTitle("老师操作界面");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        //学生成绩录入功能
        JButton btnEnterScores=new JButton("学生成绩录入");
        pane.add(btnEnterScores);
        JFrame frame=this;
        btnEnterScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterScores f = new EnterScores(courseId, credit);
                f.setVisible(true);
            }
        });
        //成绩统计排名功能
        JButton btnSelectScores=new JButton("成绩统计");
        pane.add(btnSelectScores);
        btnSelectScores.setBounds(200,80,100,80);
        btnSelectScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountFrame f =new CountFrame(courseId);
                f.setVisible(true);
            }
        });
        //查看课表功能
        JButton btnSelectCourse=new JButton("查看课表");
        pane.add(btnSelectCourse);
        btnSelectCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCourse f = new SelectCourse(teacherId);
                f.setVisible(true);
            }
        });

        //打印学生点名册
        JButton btnSelectStudent=new JButton("打印学生点名册");
        pane.add(btnSelectStudent);
        btnSelectStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectStudent f = new SelectStudent(courseId);
                f.setVisible(true);
            }
        });

        //办理调课申请、教室申请等教学业务


        JButton btnClose = new JButton("退出");
        pane.add(btnClose);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                System.exit(0);
            }
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}