package com.iflytek.voicecloud.webapi.demo.course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class expandvol extends JFrame {
    public expandvol(String[] str){
        this.setSize(500,400);
        this.setTitle("־Ը���");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTable table=new JTable();
        pane.add(table);
        JButton perselect=new JButton("־Ը��ѯ");
        pane.add(perselect);setLayout(null);perselect.setBounds(100,80,200,60);
        JButton perapply=new JButton("���־Ը��¼");
        pane.add(perapply);setLayout(null);perapply.setBounds(100,200,200,60);
        setVisible(true);
        perselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = null;PreparedStatement stmt = null;ResultSet rs = null;
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String SQL = "select * from ־Ը��Ϣ�� where StuID='"+str[0]+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    selres(rs);

                }catch (Exception ei){
                    ei.printStackTrace();
                    System.out.println("ϵͳ����");
                }
            }
        });
        perapply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plus(str);
            }
        });
    }
    public void selres(ResultSet rs){
        JFrame frame = new JFrame();
        //���ô�����������ֵ
        frame.setTitle("Volunteer selecting result");//���ô������
        frame.setSize(1000, 800);//���ô����С��ֻ�Զ���������Ч
        //frame.setDefaultCloseOperation(3);//���ô���رղ�����3��ʾ�رմ����˳�����
        frame.setLocationRelativeTo(null);//���ô����������һ���ľ���λ�ã�����null��ʾ�����������Ļ������λ��
        frame.setResizable(true);//��ֹ���������С
        frame.setFont(new Font("����", Font.PLAIN, 30));//�������壬��ʾ��ʽ��������С
        //ʵ����FlowLayout��ʽ������Ķ���ָ�����뷽ʽΪ���ж������֮��ļ��Ϊ10������
        //FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //ʵ������ʽ������Ķ���
        //frame.setLayout(fl);
        Connection con = null;
        String[] res = new String[7];
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        try {
            int i=2;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            JLabel sid = new JLabel("־Ը���");sid.setFont(new Font("����", Font.PLAIN, 20));frame.add(sid);
            JLabel cid = new JLabel("ѧ��");cid.setFont(new Font("����", Font.PLAIN, 20));frame.add(cid);
            JLabel grade = new JLabel("����");grade.setFont(new Font("����", Font.PLAIN,20));frame.add(grade);
            JLabel credit = new JLabel("רҵ");credit.setFont(new Font("����", Font.PLAIN, 20));frame.add(credit);
            JLabel gpa = new JLabel("־Ըʱ��");gpa.setFont(new Font("����", Font.PLAIN, 20));frame.add(gpa);
            JLabel pro = new JLabel("־Ը��Ŀ");pro.setFont(new Font("����", Font.PLAIN, 20));frame.add(pro);
            JLabel period = new JLabel("־Ըʱ��");period.setFont(new Font("����", Font.PLAIN, 20));frame.add(period);

            sid.setBounds(40,40,100,40);cid.setBounds(180,40,100,40);
            grade.setBounds(320,40,100,40);credit.setBounds(460,40,100,40);
            gpa.setBounds(600,40,100,40);pro.setBounds(740,40,100,40);
            period.setBounds(880,40,100,40);
            while(rs.next()){
                    res[0] = rs.getString(1);res[1] = rs.getString(2);
                    res[2] = rs.getString(3);res[3] = rs.getString(4);
                    res[4] = rs.getString(5);res[5] = rs.getString(6);
                    res[6] = res[6].valueOf(rs.getFloat(7));

                JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("����", Font.PLAIN,20));frame.add(sid1);
                JLabel cid1 = new JLabel(res[1].trim());cid1.setFont(new Font("����", Font.PLAIN,20));frame.add(cid1);
                JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("����", Font.PLAIN,20));frame.add(grade1);
                JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("����", Font.PLAIN,20));frame.add(credit1);
                JLabel gpa1 = new JLabel(res[4].trim());gpa1.setFont(new Font("����", Font.PLAIN,20));frame.add(gpa1);
                JLabel pro1 = new JLabel(res[5].trim());pro1.setFont(new Font("����", Font.PLAIN,20));frame.add(pro1);
                JLabel period1 = new JLabel(res[6].trim());period1.setFont(new Font("����", Font.PLAIN,20));frame.add(period1);
                sid1.setBounds(40,40*i,100,40);cid1.setBounds(180,40*i,100,40);
                grade1.setBounds(320,40*i,100,40);credit1.setBounds(460,40*i,100,40);
                gpa1.setBounds(600,40*i,100,40);pro1.setBounds(740,40*i,100,40);
                period1.setBounds(880,40*i,100,40);i++;
            }
            frame.setVisible(true);
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("ϵͳ����");
        }
    }
    public void plus(String[] str){
        Connection con = null;PreparedStatement stmt = null;ResultSet rs = null;
        String[] res;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dat = format.format(date);
        String SQL;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            SQL = "{call ��������(?,?,?,?,?)}";
            CallableStatement calls = con.prepareCall(SQL);
            calls.setString(1,dat); calls.setString(2,"ѧ��");
            calls.setString(3,str[0]);calls.setString(4,"���־Ը");
            calls.setString(5,null);
            calls.execute();
            calls.close();
            luchengji l = new luchengji();
            l.luru();
        }catch (Exception ei){
            ei.printStackTrace();
            System.out.println("add record error\n");
        }
    }

}
class luchengji extends JFrame{
    public void luru(){
        JFrame frame = new JFrame();
        frame.setTitle("ѧ��־Ը��Ϣ¼��");//���ô������
        frame.setSize(800, 600);//���ô����С��ֻ�Զ���������Ч
        //frame.setDefaultCloseOperation(3);//���ô���رղ�����3��ʾ�رմ����˳�����
        frame.setLocationRelativeTo(null);//���ô����������һ���ľ���λ�ã�����null��ʾ�����������Ļ������λ��
        frame.setResizable(true);//��ֹ���������С
        frame.setFont(new Font("����",Font.PLAIN,14));//�������壬��ʾ��ʽ��������С
        /*FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);*/
        frame.setLayout(null);
        String[] res = new String[6];

        JLabel txt1 = new JLabel("ѧ�ţ�");
        JTextField txtSno = new JTextField(20);frame.add(txt1);frame.add(txtSno);
        JLabel txt2 = new JLabel("������");
        JTextField txtName = new JTextField(20);frame.add(txt2);frame.add(txtName);
        JLabel txt3 = new JLabel("רҵ��");
        JTextField txtMaj = new JTextField(20);frame.add(txt3);frame.add(txtMaj);
        JLabel txt4 = new JLabel("־Ըʱ��");
        JTextField txtAge = new JTextField(20);frame.add(txt4);frame.add(txtAge);
        JLabel txt5 = new JLabel("־Ը��Ŀ ");
        JTextField voltime = new JTextField(20);frame.add(txt5);frame.add(voltime);
        JLabel txt6 = new JLabel("־Ըʱ��");
        JTextField txtCla = new JTextField(20);frame.add(txt6);frame.add(txtCla);
        txt1.setBounds(40,40,100,40);txtSno.setBounds(180,40,100,40);
        txt2.setBounds(40,100,100,40);txtName.setBounds(180,100,100,40);
        txt3.setBounds(40,160,100,40); txtMaj.setBounds(180,160,100,40);
        txt4.setBounds(40,220,100,40);txtAge.setBounds(180,220,100,40);
        txt5.setBounds(40,280,100,40); voltime.setBounds(180,280,100,40);
        txt6.setBounds(40,340,100,40);txtCla.setBounds(180,340,100,40);
        JButton btnOK=new JButton("�ύ"); frame.add(btnOK);
        btnOK.setBounds(180,400,100,40);
        frame.setVisible(true);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = null;PreparedStatement stmt = null;ResultSet rs = null;
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
                res[0] = txtSno.getText();res[1] = txtName.getText();
                res[2] = txtMaj.getText();res[3] = txtAge.getText();
                res[4] = voltime.getText();res[5] = txtCla.getText();
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    System.out.println(res[3]);
                    String SQL = "{call ־Ը����(?,?,?,?,?,?)}";
                    CallableStatement calls1 = con.prepareCall(SQL);
                    SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd" );

                    calls1.setString(1,res[0]); calls1.setString(2,res[1]);
                    calls1.setString(3,res[2]);calls1.setString(5,res[5]);
                    calls1.setString(4, res[4]);
                    calls1.setString(6,res[3]);

                    calls1.execute();
                    calls1.close();
                    SQL = "update ѧ����Ϣ�� set Stuvol=Stuvol+'"+Float.parseFloat(res[3])+"' where StuID='"+res[0]+"';";
                    stmt = con.prepareStatement(SQL);
                    int i = stmt.executeUpdate();
                }catch (Exception ei){
                    ei.printStackTrace();
                    System.out.println("submit error\n");
                }
            }
        });
    }
}
