package com.iflytek.voicecloud.webapi.demo.course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
public class Jstudentinsert extends JFrame{
	    public Jstudentinsert() {
	    	setSize(800,1000);
	        setTitle("学生信息录入");
	        Container pane=this.getContentPane();
	        //pane.setLayout(new FlowLayout(FlowLayout.LEFT));
			pane.setFont(new Font("宋体",Font.PLAIN,25));
			pane.setBounds(40,40,600,800);
	        Dimension dim = new Dimension(300,30);
			//setBounds(200,200,800,800);

			setVisible(true);
			setAlwaysOnTop(true);
			pane.setLayout(null);

	        JLabel txt1 = new JLabel("学号：");txt1.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtSno = new JTextField();
	        pane.add(txt1);pane.add(txtSno);
	        txt1.setBounds(20,20,200,40);txtSno.setBounds(300,20,200,40);

	        JLabel txt2 = new JLabel("姓名：");txt2.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtName = new JTextField();
	        pane.add(txt2);pane.add(txtName);
			txt2.setBounds(20,70,200,40);txtName.setBounds(300,70,200,40);

	        JLabel txt3 = new JLabel("专业：");txt3.setFont(new Font("宋体",Font.PLAIN,20));
	        String select2[] = {"计算机", "材料","地质勘探","会计","数学","物理","英语","油气运输","钻井"};
	        JComboBox txtMaj = new JComboBox(select2);
	        txtMaj.setPreferredSize(dim);
	        pane.add(txt3);pane.add(txtMaj);
			txt3.setBounds(20,120,200,40);txtMaj.setBounds(300,120,200,40);

	        JLabel txt4 = new JLabel("年龄：");txt4.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtAge = new JTextField(20);
	        pane.add(txt4);pane.add(txtAge);
			txt4.setBounds(20,170,200,40);txtAge.setBounds(300,170,200,40);

	        JLabel txt5 = new JLabel("政治面貌: ");txt5.setFont(new Font("宋体",Font.PLAIN,20));
	        String select3[] = {"群众", "团员","预备党员","党员"};
	        JComboBox txtPolSta = new JComboBox(select3);
	        txtPolSta.setPreferredSize(dim);
	        pane.add(txt5);pane.add(txtPolSta);
			txt5.setBounds(20,220,200,40);txtPolSta.setBounds(300,220,200,40);

	        JLabel txt6 = new JLabel("导师(可不填)： ");txt6.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtPro = new JTextField(20);
	        pane.add(txt6);pane.add(txtPro);
			txt6.setBounds(20,270,200,40);txtPro.setBounds(300,270,200,40);

	        JLabel txt7 = new JLabel("班级：");txt7.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtCla = new JTextField(20);
	        pane.add(txt7);pane.add(txtCla);
			txt7.setBounds(20,320,200,40);txtCla.setBounds(300,320,200,40);

	        JLabel txt8 = new JLabel("所在学院：");txt8.setFont(new Font("宋体",Font.PLAIN,20));
	        String select4[] = {"信息科学与工程学院", "经济管理学院","地球科学学院","新能源与材料学院","理学院","外国语学院","石油工程学院"};
	        JComboBox txtYuan = new JComboBox(select4);
	        txtYuan.setPreferredSize(dim);
	        pane.add(txt8);pane.add(txtYuan);
			txt8.setBounds(20,370,200,40);txtYuan.setBounds(300,370,200,40);
	        
	        JLabel txt9 = new JLabel("年级：");txt9.setFont(new Font("宋体",Font.PLAIN,20));
	        String select5[] = {"2016级", "2017级","2018级","2019级","2020级","2021级","2022级"};
	        JComboBox txtNian = new JComboBox(select5);
	        txtNian.setPreferredSize(dim);
	        pane.add(txt9);pane.add(txtNian);
			txt9.setBounds(20,420,200,40);txtNian.setBounds(300,420,200,40);
	        
	        JLabel txt10 = new JLabel("学生状态：");txt10.setFont(new Font("宋体",Font.PLAIN,20));
	        String select6[] = {"休学", "退学","正常修读"};
	        JComboBox txtSta = new JComboBox(select6);
	        txtSta.setPreferredSize(dim);
	        pane.add(txt10);pane.add(txtSta);
			txt10.setBounds(20,470,200,40);txtSta.setBounds(300,470,200,40);
	        
	        JLabel txt11 = new JLabel("籍贯：");txt11.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtAdd = new JTextField(20);
	        pane.add(txt11);pane.add(txtAdd);
			txt11.setBounds(20,520,200,40);txtAdd.setBounds(300,520,200,40);
	        
	        /*JLabel txt12 = new JLabel("志愿时长：");txt12.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtZhi = new JTextField(20);
	        pane.add(txt12);pane.add(txtZhi);
			txt12.setBounds(20,670,200,40);txtZhi.setBounds(300,670,200,40);*/
	        
	        JLabel txt13 = new JLabel("学费：");txt13.setFont(new Font("宋体",Font.PLAIN,20));
	        JTextField txtFee = new JTextField(20);
	        pane.add(txt13);pane.add(txtFee);
			txt13.setBounds(20,570,200,40);txtFee.setBounds(300,570,200,40);
	        
	        JLabel txt14 = new JLabel("是否缴费：");txt14.setFont(new Font("宋体",Font.PLAIN,20));
	        String select1[] = {"否"};
	        JComboBox txtIsF = new JComboBox(select1);
	        txtIsF.setPreferredSize(dim);
	        pane.add(txt14);pane.add(txtIsF);
			txt14.setBounds(20,620,200,40);txtIsF.setBounds(300,620,200,40);
	        
	        JButton btnOK=new JButton("确定添加学生信息");
	        pane.add(btnOK);
	        btnOK.setBounds(50,770,200,80);
	        JFrame frame=this;
	        btnOK.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if(txtSno.getText().length()==0||txtName.getText().length()==0 || txtMaj.getSelectedItem().toString().length()==0
	                        ||txtCla.getText().length()==0||txtYuan.getSelectedItem().toString().length()==0||txtNian.getSelectedItem().toString().length()==0
	                        ||txtSta.getSelectedItem().toString().length()==0) {
	                    JOptionPane.showMessageDialog(frame, "学号，姓名，专业，班级，学院，年级，学生状态必填！");
	                    return;
	                }
	                //if
	                // Create a variable for the connection string.
	                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";

	                // Declare the JDBC objects.
	                Connection con = null;
	                Statement stmt = null;
	                ResultSet rs = null;
					CallableStatement c;
	                try {
						// Establish the connection.
						//com.microsoft.jdbc.Sqlserver.SQLServerDriver
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						con = DriverManager.getConnection(connectionUrl, "sa", "159357");
						float r;
						if (txtIsF.getSelectedItem().toString().equals("是")) {
							r = 1;
						} else r = 0;

						// Create and execute an SQL statement that returns some data.
	                    /*String SQL = "insert into 学生信息表(StuID,Stuname,Stupro,Stuage,Iden,Clanum,College,Grade,Cond,Location,Tuition,Contui)"
	                            +"values('"+txtSno.getText()+"','"+txtName.getText()+"','"+txtMaj.getSelectedItem().toString()
	                            +"','"+txtAge.getText()+"','"+txtPolSta.getSelectedItem().toString()
	                            +"','"+txtCla.getText()+"','"+txtYuan.getSelectedItem().toString()+"','"+txtNian.getSelectedItem().toString()
	                            +"','"+txtSta.getSelectedItem().toString()+"','"+txtAdd.getText()
	                            +"','"+txtFee.getText()+"','"+r+"')";
	                    stmt = con.createStatement();
	                    int result = stmt.executeUpdate(SQL);
	                    if(result==1)
	                        JOptionPane.showMessageDialog(frame, "插入成功！");}*/

						c = con.prepareCall("{call 插入学生(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
						c.setString(1, txtSno.getText());
						c.setString(2, txtName.getText());
						c.setString(3, txtMaj.getSelectedItem().toString());
						c.setString(4, txtAge.getText());
						c.setString(5, txtPolSta.getSelectedItem().toString());
						c.setString(6, txtPro.getText());
						c.setString(7, txtCla.getText());
						c.setString(8, txtYuan.getSelectedItem().toString());
						c.setString(9, txtNian.getSelectedItem().toString());
						c.setString(10, txtSta.getSelectedItem().toString());
						c.setString(11, txtAdd.getText());
						c.setString(12, txtFee.getText());
						c.setFloat(13, r);
						boolean jud = c.execute();
						if(!jud)
							JOptionPane.showMessageDialog(frame, "插入成功！");
						System.out.println(jud);
						c.close();
					}

	                // Iterate through the data in the result set and display it.
	                // Handle any errors that may have occurred.
	                catch (com.microsoft.sqlserver.jdbc.SQLServerException ex)
	                {
	               		JOptionPane.showMessageDialog(frame, "插入失败！");
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
	        JButton btnClose = new JButton("退出");
	        pane.add(btnClose);
			btnClose.setBounds(400,770,200,80);
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