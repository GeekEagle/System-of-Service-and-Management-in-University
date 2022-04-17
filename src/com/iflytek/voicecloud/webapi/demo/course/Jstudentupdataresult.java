package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentupdataresult extends JFrame{
    public Jstudentupdataresult(String ID) {
        this.setSize(1800,1000);
        this.setTitle("ѧ����Ϣ����");
        Container pane=this.getContentPane();
        pane.setFont(new Font("����",Font.PLAIN,20));
        pane.setBounds(40,40,600,800);
        setVisible(true);
        setAlwaysOnTop(true);
        pane.setLayout(null);
        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] listname= {"ѧ��","����","רҵ","����",
                "������ò","��ʦ","�༶��","ѧԺ",
                "�꼶","ѧ��״̬","����","־Ըʱ��",
                "ѧ��","�Ƿ�ɷ�","����"};

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String[][] count=new String[1][15];
        try {
            // Establish the connection.
            //com.microsoft.jdbc.Sqlserver.SQLServerDriver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            // Create and execute an SQL statement that returns some data.
            String SQL = "select * from ѧ����Ϣ��_view where StuID="+ID;
            stmt = con.createStatement();
            int i=0;
            rs =stmt.executeQuery(SQL);
            while(rs.next()) {
                count[i]=new String[] {rs.getString("StuID"),rs.getString("Stuname"),
                        rs.getString("Stupro"),rs.getString("Stuage"),
                        rs.getString("Iden"),rs.getString("Teanum"),
                        rs.getString("Clanum"),rs.getString("College"),
                        rs.getString("Grade"),rs.getString("Cond"),
                        rs.getString("Location"),rs.getString("Stuvol"),
                        rs.getString("Tuition"),rs.getString("Contui"),
                        rs.getString("gpa")};
                i++;
            }
        }

        // Iterate through the data in the result set and display it.
        // Handle any errors that may have occurred.
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
        };
        JTable table =new JTable(count,listname);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(20,20,1700,200);
        table.setBounds(40,40,1800,200);
        table.setFont(new Font("����",Font.PLAIN,20));
        table.setRowHeight(30);
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        pane.add(scrollPane, BorderLayout.NORTH);
        Dimension dim = new Dimension(300,30);
        /*JLabel txt1 = new JLabel("ѧ�ţ�");
        JTextField txtSno = new JTextField(20);
        pane.add(txt1, BorderLayout.CENTER);
        pane.add(txtSno, BorderLayout.CENTER);
        txt1.setBounds(20,250,200,40);txtSno.setBounds(300,250,200,40);
*/

        JLabel txt2 = new JLabel("������");
        JTextField txtName = new JTextField(20);
        pane.add(txt2);
        pane.add(txtName);
        txt2.setBounds(20,300,200,40);txtName.setBounds(300,300,200,40);


        JLabel txt3 = new JLabel("רҵ��");
        String select2[] = {"�����", "����","���ʿ�̽","���","��ѧ","����","Ӣ��","��������","�꾮"};
        JComboBox txtMaj = new JComboBox(select2);
        txtMaj.setPreferredSize(dim);
        pane.add(txt3);pane.add(txtMaj);
        txt3.setBounds(20,350,200,40);txtMaj.setBounds(300,350,200,40);


        JLabel txt4 = new JLabel("���䣺");
        JTextField txtAge = new JTextField(20);
        pane.add(txt4);
        pane.add(txtAge);
        txt4.setBounds(20,400,200,40);txtAge.setBounds(300,400,200,40);


        JLabel txt5 = new JLabel("������ò: ");
        String select3[] = {"Ⱥ��", "��Ա","Ԥ����Ա","��Ա"};
        JComboBox txtPolSta = new JComboBox(select3);
        txtPolSta.setPreferredSize(dim);
        pane.add(txt5);
        pane.add(txtPolSta);
        txt5.setBounds(20,450,200,40);txtPolSta.setBounds(300,450,200,40);


        //JLabel txt6 = new JLabel("��ʦ�� ");
        //JTextField txtPro = new JTextField(20);
        //pane.add(txt6);
        //pane.add(txtPro);

        JLabel txt7 = new JLabel("�༶��");
        JTextField txtCla = new JTextField(20);
        pane.add(txt7);
        pane.add(txtCla);
        txt7.setBounds(20,500,200,40);txtCla.setBounds(300,500,200,40);


        JLabel txt8 = new JLabel("����ѧԺ��");
        String select4[] = {"��Ϣ��ѧ�빤��ѧԺ", "���ù���ѧԺ","�����ѧѧԺ","����Դ�����ѧԺ","��ѧԺ","�����ѧԺ","ʯ�͹���ѧԺ"};
        JComboBox txtYuan = new JComboBox(select4);
        txtYuan.setPreferredSize(dim);
        pane.add(txt8);
        pane.add(txtYuan);
        txt8.setBounds(20,550,200,40);txtYuan.setBounds(300,550,200,40);


        JLabel txt9 = new JLabel("�꼶��");
        String select5[] = {"2016��", "2017��","2018��","2019��","2020��","2021��","2022��"};
        JComboBox txtNian = new JComboBox(select5);
        txtNian.setPreferredSize(dim);
        pane.add(txt9);pane.add(txtNian);
        txt9.setBounds(20,600,200,40);txtNian.setBounds(300,600,200,40);


        JLabel txt10 = new JLabel("ѧ��״̬��");
        String select[] = {"��ѧ", "��ѧ","�����޶�"};
        JComboBox txtSta = new JComboBox(select);
        txtSta.setPreferredSize(dim);
        pane.add(txt10);
        pane.add(txtSta);
        txt10.setBounds(20,650,200,40);txtSta.setBounds(300,650,200,40);


        JLabel txt11 = new JLabel("���᣺");
        JTextField txtAdd = new JTextField(20);
        pane.add(txt11);
        pane.add(txtAdd);
        txt11.setBounds(20,700,200,40);txtAdd.setBounds(300,700,200,40);


        //JLabel txt12 = new JLabel("־Ըʱ����");
        //JTextField txtZhi = new JTextField(20);
        //pane.add(txt12);
        //pane.add(txtZhi);

        JLabel txt13 = new JLabel("ѧ�ѣ�");
        JTextField txtFee = new JTextField(20);
        pane.add(txt13);
        pane.add(txtFee);
        txt13.setBounds(20,750,200,40);txtFee.setBounds(300,750,200,40);


        JLabel txt14 = new JLabel("�Ƿ�ɷѣ�");
        String select1[] = {"��", "��"};
        JComboBox txtIsF = new JComboBox(select1);
        txtIsF.setPreferredSize(dim);
        pane.add(txt14);
        pane.add(txtIsF);
        txt14.setBounds(20,800,200,40);txtIsF.setBounds(300,800,200,40);


        JButton btnOK=new JButton("ȷ���޸�ѧ����Ϣ");
        pane.add(btnOK);
        btnOK.setBounds(800,350,200,80);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtName.getText().length()==0 || txtMaj.getSelectedItem().toString().length()==0
                        ||txtCla.getText().length()==0||txtYuan.getSelectedItem().toString().length()==0||txtNian.getSelectedItem().toString().length()==0
                        ||txtSta.getSelectedItem().toString().length()==0) {
                    JOptionPane.showMessageDialog(frame, "ѧ�ţ�������רҵ���༶��ѧԺ���꼶��ѧ��״̬���");
                    return;
                }
                //if
                // Create a variable for the connection string.
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

                // Declare the JDBC objects.
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    float r;
                    if(txtIsF.getSelectedItem().toString().equals("��"))
                    {
                        r=1;
                    }
                    else r=0;

                    // Create and execute an SQL statement that returns some data.
                    String SQL = "Update ѧ����Ϣ�� set StuID="+"'"+ID+"'"+",Stuname="+"'"+txtName.getText()+"'"+",Stupro="+"'"+txtMaj.getSelectedItem().toString()+"'"+
                            ",Stuage="+txtAge.getText()+",Iden="+"'"+txtPolSta.getSelectedItem().toString()+"'"+",Clanum="+"'"+txtCla.getText()+"'"+",College="+"'"+txtYuan.getSelectedItem().toString()+"'"+
                            ",Grade="+"'"+txtNian.getSelectedItem().toString()+"'"+",Cond="+"'"+txtSta.getSelectedItem().toString()+"'"+",Location="+"'"+txtAdd.getText()+"'"+
                            ",Tuition="+txtFee.getText()+",Contui="+r+" where StuID="+ID;
                    stmt = con.createStatement();
                    int result = stmt.executeUpdate(SQL);
                    if(result==1)
                    {
                        JOptionPane.showMessageDialog(frame, "�޸ĳɹ���");
                        frame.setVisible(false);
                        Jstudentupdataresult f = new Jstudentupdataresult(ID);
                        f.setVisible(true);
                    }
                }
                // Iterate through the data in the result set and display it.
                // Handle any errors that may have occurred.
                catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
                {
                    JOptionPane.showMessageDialog(frame, "�޸�ʧ�ܣ�");
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
            }

        });
        JButton btnClose = new JButton("�˳�");
        pane.add(btnClose);
        btnClose.setBounds(800,650,200,80);
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