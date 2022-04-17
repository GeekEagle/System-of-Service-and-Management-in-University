package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.Container;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.swing.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class JiaowuchuFrame extends JFrame{
    public  JiaowuchuFrame(String ID) {
        this.setSize(1000,800);
        this.setTitle("教务处界面");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        System.out.println("hahahahaha");
        JTable table=new JTable();
        pane.add(table);

        //学生管理功能
        JButton btnAdd=new JButton("学生管理功能");
        pane.add(btnAdd);
        setLayout(null);
        btnAdd.setBounds(100,80,200,160);
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JstudentFrame f =new JstudentFrame();
                f.setVisible(true);
            }
        });

        //学生教师管理功能
        JButton btnAdd2=new JButton("学生教师管理功能");
        pane.add(btnAdd2);
        btnAdd2.setBounds(300,80,200,160);
        btnAdd2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JStudentTeacherFrame f =new JStudentTeacherFrame();
                f.setVisible(true);
            }
        });

        //修改密码功能
        JButton btnchange=new JButton("修改密码功能");
        pane.add(btnchange);
        btnchange.setBounds(500,80,200,160);
        btnchange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                pw_ch_Frame f =new pw_ch_Frame(ID);
                f.setVisible(true);
            }
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

