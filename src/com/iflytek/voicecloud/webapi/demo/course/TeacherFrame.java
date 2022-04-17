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
            String SQL = "select CourseID,Credit from �γ���Ϣ��_view where TeacherID = " + teacherId;
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
        this.setTitle("��ʦ��������");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        //ѧ���ɼ�¼�빦��
        JButton btnEnterScores=new JButton("ѧ���ɼ�¼��");
        pane.add(btnEnterScores);
        JFrame frame=this;
        btnEnterScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterScores f = new EnterScores(courseId, credit);
                f.setVisible(true);
            }
        });
        //�ɼ�ͳ����������
        JButton btnSelectScores=new JButton("�ɼ�ͳ��");
        pane.add(btnSelectScores);
        btnSelectScores.setBounds(200,80,100,80);
        btnSelectScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountFrame f =new CountFrame(courseId);
                f.setVisible(true);
            }
        });
        //�鿴�α���
        JButton btnSelectCourse=new JButton("�鿴�α�");
        pane.add(btnSelectCourse);
        btnSelectCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCourse f = new SelectCourse(teacherId);
                f.setVisible(true);
            }
        });

        //��ӡѧ��������
        JButton btnSelectStudent=new JButton("��ӡѧ��������");
        pane.add(btnSelectStudent);
        btnSelectStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectStudent f = new SelectStudent(courseId);
                f.setVisible(true);
            }
        });

        //����������롢��������Ƚ�ѧҵ��


        JButton btnClose = new JButton("�˳�");
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