package com.iflytek.voicecloud.webapi.demo.course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class EntryFrame extends JFrame{
    public  EntryFrame() {
        this.setSize(300,300);
        this.setTitle("学籍录入");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel txt1 = new JLabel("学号：");
        JTextField txtSno = new JTextField(20);
        pane.add(txt1);
        pane.add(txtSno);

        JLabel txt2 = new JLabel("姓名：");
        JTextField txtName = new JTextField(20);
        pane.add(txt2);
        pane.add(txtName);

        JLabel txt3 = new JLabel("性别：");
        JTextField txtSex = new JTextField(20);
        pane.add(txt3);
        pane.add(txtSex);

        JLabel txt4 = new JLabel("年龄：");
        JTextField txtAge = new JTextField(20);
        pane.add(txt4);
        pane.add(txtAge);

        JLabel txt5 = new JLabel("系别：");
        JTextField txtDeptno = new JTextField(20);
        pane.add(txt5);
        pane.add(txtDeptno);

        JLabel txt6 = new JLabel("专业：");
        JTextField txtPro = new JTextField(20);
        pane.add(txt6);
        pane.add(txtPro);

        JLabel txt7 = new JLabel("学习情况：");
        JTextField txtGra = new JTextField(20);
        pane.add(txt7);
        pane.add(txtGra);

        JLabel txt8 = new JLabel("联系方式：");
        JTextField txtTel = new JTextField(20);
        pane.add(txt8);
        pane.add(txtTel);

        JButton btnOK=new JButton("添加学生信息");
        pane.add(btnOK);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtSno.getText().length()==0||txtName.getText().length()==0 || txtDeptno.getText().length()==0
                        ||txtPro.getText().length()==0||txtGra.getText().length()==0) {
                    JOptionPane.showMessageDialog(frame, "学号，姓名，系别，专业，学习情况必填，但是你没有填！");
                    return;
                }
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


                    // Create and execute an SQL statement that returns some data.
                    String SQL = "insert into Student(sno,sname,sex,age,deptno,profession,graduation,tel)"
                            +"values('"+txtSno.getText()+"','"+txtName.getText()+"','"+txtSex.getText()
                            +"','"+txtAge.getText()+"','"+txtDeptno.getText()+"','"+txtPro.getText()
                            +"','"+txtGra.getText()+"','"+txtTel.getText()+"')";
                    stmt = con.createStatement();
                    int result = stmt.executeUpdate(SQL);

                    if(result==1)
                        JOptionPane.showMessageDialog(frame, "插入成功！");}
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
                }
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
