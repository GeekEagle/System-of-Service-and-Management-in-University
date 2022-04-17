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
	
	//GUI��������
	private JTable table; 
	private JTextArea inputQuery; 
	private JButton submitQuery;
    Connection con = null;
	public  JstudentFrame()  {
		this.setSize(1000,800);
        this.setTitle("ѧ������");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTable table=new JTable();
        pane.add(table);
		
      //ѧ����ѯ����
        JButton jsselect=new JButton("ѧ����ѯ��Ϣ����");
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
      //ѧ����Ϣ¼�빦��
        JButton jsinsert=new JButton("ѧ����Ϣ¼�빦��");
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
        //ѧ����Ϣ���¹���
        JButton jsupdata=new JButton("ѧ����Ϣ���¹���");
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
      //����ѧ�����������
        JButton jsdo=new JButton("����ѧ�����������");
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
      //ѧ������������
        JButton jshonor=new JButton("ѧ������������");
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
      //ѧ�����ֹ�����
        JButton jspun=new JButton("ѧ�����ֹ�����");
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
        //ѧ��ǩ�������ѯ����
        JButton jssign=new JButton("ѧ��ǩ�������ѯ����");
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
        JButton btnClose = new JButton("�˳�");
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
