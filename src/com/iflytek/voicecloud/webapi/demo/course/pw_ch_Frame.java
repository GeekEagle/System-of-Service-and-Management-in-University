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


public class pw_ch_Frame extends JFrame{
    public pw_ch_Frame(String id ) {
        this.setSize(300,300);
        this.setTitle("�������");
        Container pane=this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel txt1 = new JLabel("ԭ����");
        JTextField txtoldpw = new JTextField(20);
        pane.add(txt1);
        pane.add(txtoldpw);

        JLabel txt2 = new JLabel("������");
        JTextField txtnewpw = new JTextField(20);
        pane.add(txt2);
        pane.add(txtnewpw);

        JLabel txt3 = new JLabel("ȷ������");
        JTextField txtcheckpw = new JTextField(20);
        pane.add(txt3);
        pane.add(txtcheckpw);


        JButton btnOK=new JButton("ȷ��");
        pane.add(btnOK);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Create a variable for the connection string.
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

                // Declare the JDBC objects.

                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;

                String  ps=new String();
                try {
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                    String SQLs = "select ��¼��Ϣ��.password from ��¼��Ϣ�� where userid=" + id;
                    stmt = con.createStatement();
                    ResultSet qry = stmt.executeQuery(SQLs);
                    while(qry.next())
                        ps=qry.getString(1);
                    if(ps!=null)
                    {
                        if((txtoldpw.getText()).equals(ps.trim()))
                        {
                            if(txtnewpw.getText().equals(txtcheckpw.getText()))
                            {
                                if(txtnewpw.getText().length()<20 && txtnewpw.getText().length()>=6)
                                {
                                    String SQL = "update ��¼��Ϣ�� set ��¼��Ϣ��.password="+txtnewpw.getText()+" where userid="+id;
                                    int result = stmt.executeUpdate(SQL);
                                    if (result == 1)
                                        JOptionPane.showMessageDialog(frame, "�޸ĳɹ���");
                                    frame.setVisible(false);
                                }
                                else
                                    JOptionPane.showMessageDialog(frame, "������������");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(frame, "ȷ�������������벻ͬ");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "ԭ�������");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "�û���������");
                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();
                } finally{
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
