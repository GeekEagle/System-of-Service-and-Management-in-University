package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
public class JStudentTeacherFrame extends JFrame{

	public  JStudentTeacherFrame()  {
		this.setSize(1000,800);
        this.setTitle("教务处学生教师管理");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTable table=new JTable();
        pane.add(table);
		
      //学生老师查询功能
        JButton btnAdd=new JButton("学生老师查询功能");
        pane.add(btnAdd);
        setLayout(null);
        btnAdd.setBounds(100,80,150,150);
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	JstudentteacherselectFrame f =new JstudentteacherselectFrame();
                f.setVisible(true);
            }
        });
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
