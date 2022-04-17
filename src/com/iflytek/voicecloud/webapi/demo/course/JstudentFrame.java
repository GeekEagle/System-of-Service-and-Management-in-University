package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class JstudentFrame extends JFrame{

	private Statement statement; 
	private ResultSet resultSet;
	
	//GUI变量定义
	private JTable table; 
	private JTextArea inputQuery; 
	private JButton submitQuery;
    Connection con = null;
	public  JstudentFrame()  {
		this.setSize(1000,800);
        this.setTitle("学生管理");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTable table=new JTable();
        pane.add(table);
		
      //学籍查询功能
        JButton jsselect=new JButton("学生查询信息功能");
        pane.add(jsselect);
        setLayout(null);
        jsselect.setBounds(100,80,200,160);
        JFrame frame=this;
        jsselect.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

            	Jstudentselect f =new Jstudentselect();
                f.setVisible(true);
        	}
        });
      //学生信息录入功能
        JButton jsinsert=new JButton("学生信息录入功能");
        pane.add(jsinsert);
        setLayout(null);
        jsinsert.setBounds(300,80,200,160);
        jsinsert.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudentinsert f =new Jstudentinsert();
                f.setVisible(true);
        	}
        });
        //学生信息更新功能
        JButton jsupdata=new JButton("学生信息更新功能");
        pane.add(jsupdata);
        setLayout(null);
        jsupdata.setBounds(500,80,200,160);
        jsupdata.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudentupdata f =new Jstudentupdata();
                f.setVisible(true);
        	}
        });
      //处理学生待办事项功能
        JButton jsdo=new JButton("处理学生待办事项功能");
        pane.add(jsdo);
        setLayout(null);
        jsdo.setBounds(700,80,200,160);
        jsdo.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudentdo f =new Jstudentdo();
                f.setVisible(true);
        	}
        });
      //学生荣誉管理功能
        JButton jshonor=new JButton("学生荣誉管理功能");
        pane.add(jshonor);
        setLayout(null);
        jshonor.setBounds(700,300,200,160);
        jshonor.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudenthonor f =new Jstudenthonor();
                f.setVisible(true);
        	}
        });
      //学生处分管理功能
        JButton jspun=new JButton("学生处分管理功能");
        pane.add(jspun);
        setLayout(null);
        jspun.setBounds(500,300,200,160);
        jspun.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudentpunish f =new Jstudentpunish();
                f.setVisible(true);
        	}
        });
        //学生签到情况查询功能
        JButton jssign=new JButton("学生签到情况查询功能");
        pane.add(jssign);
        setLayout(null);
        jssign.setBounds(300,300,200,160);
        jssign.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {

        		Jstudentsign f =new Jstudentsign();
                f.setVisible(true);
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
