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

public class EnterScores extends JFrame {
    JLabel txt1 = new JLabel("ѧ�ţ�");
    public static JTextField txtSno = new JTextField(20);
    JLabel txt3 = new JLabel("�ɼ���");
    public static JTextField txtScores = new JTextField(20);

    public EnterScores(String courseId, String credit) {
        this.setSize(600, 600);
        this.setTitle("�ɼ�¼��");
        Container pane = this.getContentPane();
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setLocationRelativeTo(null);

        pane.add(txt1);
        pane.add(txtSno);

        JLabel txt2 = new JLabel("�γ̺ţ�");
        JTextField txtCno = new JTextField(20);
        txtCno.setText(courseId);
        txtCno.setEditable(false);
        pane.add(txt2);
        pane.add(txtCno);

        pane.add(txt3);
        pane.add(txtScores);

        JLabel txt4 = new JLabel("ѧ�֣�");
        JTextField txtCredit = new JTextField(20);
        txtCredit.setText(credit);
        txtCredit.setEditable(false);
        pane.add(txt4);
        pane.add(txtCredit);

        //����¼��ɼ�
        JButton btnSound=new JButton("����¼��ɼ�");
        pane.add(btnSound);
        btnSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {
                Sound f = new Sound();
                try
                {
                    f.save("E:\\code\\java_sqlserver\\record\\test.pcm");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        JButton btnOK=new JButton("¼��");
        pane.add(btnOK);
        JFrame frame=this;
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtSno.getText().length()==0||txtCno.getText().length()==0 || txtScores.getText().length()==0) {
                    JOptionPane.showMessageDialog(frame, "ѧ�ţ��ɼ�������������û���");
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
                   /* String SQL = "insert into ѡ����Ϣ��_view(StuID, CourseID, Grade, Credit)"
                            +"values('"+txtSno.getText()+"','"+txtCno.getText()+"','"+txtScores.getText()+"','"+txtCredit.getText()+"')";*/
                    String SQL = "update ѡ����Ϣ��_view set Grade = " + txtScores.getText() + " where StuID = " + txtSno.getText();
                    stmt = con.createStatement();
                    int result = stmt.executeUpdate(SQL);
                    if(result==1)
                        JOptionPane.showMessageDialog(frame, "����ɹ���");
                    txtSno.setText("");
                    txtScores.setText("");
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
                }
            }
        });
    }
}