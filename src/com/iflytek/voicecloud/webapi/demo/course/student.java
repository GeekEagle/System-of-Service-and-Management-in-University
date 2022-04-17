package com.iflytek.voicecloud.webapi.demo.course;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*import com.microsoft.sqlserver.jdbc.SQLServerDataSource;*/
public class student {
    Image keyBoard = null;
    AudioClip[] notes1 = new AudioClip[13]; // a1 - g1
    Button[] btns = new Button[13];
    JButton start = new JButton("��ʼ");
    JButton end  = new JButton("ֹͣ");

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //���������У�ʵ����Login��Ķ��󣬵��ó�ʼ������ķ���
        student log = new student();
        log.initUI();
    }
    public void initUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");//���ô������
        frame.setSize(800, 500);//���ô����С��ֻ�Զ���������Ч
        //frame.setDefaultCloseOperation(3);//���ô���رղ�����3��ʾ�رմ����˳�����
        frame.setLocationRelativeTo(null);//���ô����������һ���ľ���λ�ã�����null��ʾ�����������Ļ������λ��
        frame.setResizable(false);//��ֹ���������С
        frame.setFont(new Font("����",Font.PLAIN,14));//�������壬��ʾ��ʽ��������С
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);

        //ʵ����JLabel��ǩ���󣬸ö�����ʾ���˺š�
        JLabel labname = new JLabel("�˺ţ�");labname.setFont(new Font("����",Font.PLAIN,14));frame.add(labname);

        JTextField text_name = new JTextField();Dimension dim1 = new Dimension(300,30);
        text_name.setPreferredSize(dim1);frame.add(text_name);

        //ʵ����JLabel��ǩ���󣬸ö�����ʾ�����롱
        JLabel labpass = new JLabel("���룺");labpass.setFont(new Font("����",Font.PLAIN,14));frame.add(labpass);
        //ʵ����JPasswordField
        JPasswordField text_password = new JPasswordField();text_password.setPreferredSize(dim1);frame.add(text_password);
        JLabel labrole = new JLabel("��ɫ��");labrole.setFont(new Font("����",Font.PLAIN,14));frame.add(labrole);

        String str[] = {"student", "teacher", "manager"};JComboBox role = new JComboBox(str);
        role.setPreferredSize(dim1);frame.add(role);

        //ʵ����JButton���
        JButton button1 = new JButton();Dimension dim2 = new Dimension(100,30);button1.setText("��¼");button1.setFont(new Font("����",Font.PLAIN,14));
        //���ð�����С
        button1.setSize(dim2);
        frame.add(button1);

        frame.setVisible(true);//����ɼ���һ��Ҫ��������������봰���
        //String rol=role.getSelectedItem().toString();
        Listeners ll = new Listeners(frame,text_name,text_password,role);
        button1.addActionListener(ll);
    }
}

class Listeners implements ActionListener {
    private JTextField text_name;
    private JPasswordField text_password;
    private JFrame login;
    private JComboBox role;
    public Listeners(JFrame login, JTextField text_name, JPasswordField text_password, JComboBox role)
    {
        //��ȡ��¼���桢�˺�������������
        this.login=login;
        this.text_name=text_name;
        this.text_password=text_password;
        this.role = role;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(text_name.getText().length()==0||text_password.getText().length()==0)
        {
            JOptionPane.showMessageDialog(login, "please input text");
            return;
        }

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Establish the connection.
            //com.microsoft.jdbc.Sqlserver.SQLServerDriver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            // Create and execute an SQL statement that returns some data.
            String shenfen = role.getSelectedItem().toString().trim();
            String SQL = "select userid,password,role" +" from ��¼��Ϣ��"+" where userid="+text_name.getText()+"and password="+text_password.getText();
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if(rs.next()){
                if ( shenfen.equals("student")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(1,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"�û���ݲ�ƥ��");
                }
                else if (shenfen.equals("teacher")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(2,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"�û���ݲ�ƥ��");
                }
                else if (shenfen.equals("manager")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(3,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"�û���ݲ�ƥ��");
                    System.out.println("hahahahahahaha");
                }
            }
            else JOptionPane.showMessageDialog(login,"�˺Ż��������");

        }
        // Iterate through the data in the result set and display it.
        // Handle any errors that may have occurred.
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
    }
}

class entrance extends JFrame implements ActionListener{
    private Container con;
    private JComboBox opti;
    private String opreating;
    private String[] id=new String[15];
    public static int num=1000;
    public JButton veri;
    public JComboBox opt;
    public int show;
    public JButton veri1;
    public JComboBox dir;
    public entrance(Container c,JButton veri,JComboBox opt,JButton veri1,JComboBox dir,JComboBox opti,String opreating,String[] id)
    {
        //��ȡ��������
        this.con = c;
        this.opti = opti;
        this.opreating = opreating;
        this.id = id;        //ѧ����Ϣ
        this.opt = opt;      //ѡ�������˵�
        this.veri = veri;    //ѡ��ȷ������
        this.veri1 = veri1;  //תרҵȷ��
        this.dir = dir;    //תרҵ�����˵�
    }
    public entrance() {}
    public void init(int ver,JFrame login2,JTextField text_name ){

        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "159357");
            // Create and execute an SQL statement that returns some data.
            String SQL = new String();
            if (ver==1){
                System.out.println("jhahahhah");
                SQL = "select *" + " from ѧ����Ϣ��" + " where StuID="+text_name.getText().trim();
                stmt = con.prepareStatement(SQL);
                // stmt.setString(1,text_name.getText());
                rs = stmt.executeQuery();
                setBounds(200,200,800,800);
                setTitle("ѧ����¼");
                // if(ver==2){setTitle("��ʦ��¼");}
                //if(ver==3){setTitle("���񴦵�¼");}
                Container c = getContentPane();
                //c.setLayout(new FlowLayout(FlowLayout.LEFT));
                setVisible(true);
                setAlwaysOnTop(true);
                c.setLayout(null);
                /*��ʾѧ����Ϣ*/
                JLabel stunum = new JLabel("ѧ��");JLabel stuname = new JLabel("����");JLabel stupro = new JLabel("רҵ");
                JLabel stuage = new JLabel("����");JLabel iden = new JLabel("������ò");JLabel teanum = new JLabel("��ʦ");
                JLabel clanum = new JLabel("�༶��");JLabel college = new JLabel("����ѧԺ");JLabel grade = new JLabel("�꼶");
                JLabel cond = new JLabel("ѧ��״̬");JLabel location = new JLabel("����");JLabel stuvol = new JLabel("־Ըʱ��");
                JLabel Tuition = new JLabel("ѧ��");JLabel Contui = new JLabel("�Ƿ�ɷ�");JLabel gpa = new JLabel("����");
                String select[] = {"ѡ����Ϣ��", "�༶��Ϣ��","����Ϣ��","������Ϣ��","������¼��"};
                JComboBox table = new JComboBox(select);
                Dimension dim1 = new Dimension(300,30);
                table.setPreferredSize(dim1);
                String req[] = {"ѡ��", "תרҵ", "��ѧ","��ѧ��","��ѧ"};
                JComboBox issue = new JComboBox(req);
                table.setPreferredSize(dim1);
                c.add(table);
                c.add(issue);
                /*String choose = table.getSelectedItem().toString().trim();
                String doing = issue.getSelectedItem().toString().trim();*/

                String str[] = new String[15];
                int i=0;
                if(rs.next()){
                    str[0] = rs.getString(1);str[1] = rs.getString(2);str[2] = rs.getString(3);
                    str[3] = str[3].valueOf(rs.getInt(4));str[4] = rs.getString(5);str[5] = rs.getString(6);
                    str[6] = rs.getString(7);str[7] = rs.getString(8);str[8] = rs.getString(9);
                    str[9] = rs.getString(10);str[10] = rs.getString(11);str[11] = rs.getString(12);
                    str[12] = str[12].valueOf(rs.getInt(13));str[13] = rs.getString(14);
                    Object obj= rs.getObject(15); if(obj!=null){str[14] = str[14].valueOf(obj);}
                }
                JTextArea resume1=new JTextArea(20,30);
                JButton check = new JButton("��ѯ");
                JButton apply = new JButton("����");
                JButton volun = new JButton("�޸�־Ը");
                JButton ccp = new JButton("�������");
                String course[] = {"�ߵ���ѧ", "���ݿ�ϵͳ", "��������ԭ��","���ݽṹ","����ԭ��","������������ͳ��","����ѧ"};JComboBox option = new JComboBox(course);
                option.setPreferredSize(dim1);c.add(option);
                JButton veri = new JButton("ȷ��");c.add(veri);
                String major[] = {"����", "�����", "��е","ʯ��","����","��ȫ","����"};JComboBox direct = new JComboBox(major);
                direct.setPreferredSize(dim1);c.add(direct);
                JButton veri1 = new JButton("ȷ��");c.add(veri1);

                JLabel stunum1 = new JLabel(str[0]);JLabel stuname1 = new JLabel(str[1]);JLabel stupro1 = new JLabel(str[2]);
                JLabel stuage1 = new JLabel(str[3]);JLabel iden1 = new JLabel(str[4]);JLabel teanum1 = new JLabel(str[5]);
                JLabel clanum1 = new JLabel(str[6]);JLabel college1 = new JLabel(str[7]);JLabel grade1 = new JLabel(str[8]);
                JLabel cond1 = new JLabel(str[9]);JLabel location1 = new JLabel(str[10]);JLabel stuvol1 = new JLabel(str[11]);
                JLabel Tuition1 = new JLabel(str[12]);JLabel Contui1 = new JLabel(str[13]);JLabel gpa1 = new JLabel(str[14]);
                c.add(stunum);c.add(stunum1);c.add(stuname); c.add(stuname1);c.add(stupro);c.add(stupro1);
                c.add(stuage); c.add(stuage1);c.add(iden);c.add(iden1);c.add(teanum); c.add(teanum1);
                c.add(clanum);c.add(clanum1);c.add(college); c.add(college1);c.add(grade);c.add(grade1);
                c.add(cond); c.add(cond1);c.add(location);c.add(location1);c.add(stuvol); c.add(stuvol1);
                c.add(Tuition);c.add(Tuition1);c.add(Contui); c.add(Contui1);c.add(gpa); c.add(gpa1);
                c.add(check);c.add(apply);c.add(volun);c.add(ccp);
                stunum.setBounds(20, 40, 80, 18);stunum1.setBounds(120, 40, 80, 30);
                stuname.setBounds(20, 60, 80, 18);stuname1.setBounds(120, 60, 80, 30);
                stupro.setBounds(20, 80, 80, 18);stupro1.setBounds(120, 80, 80, 30);
                iden.setBounds(20, 100, 80, 18); iden1.setBounds(120, 100, 80, 30);
                teanum.setBounds(20, 120, 80, 18);teanum1.setBounds(120, 120, 80, 30);
                clanum.setBounds(20,140,80,18);clanum1.setBounds(120,140,80,30);
                college.setBounds(20,160,80,18);college1.setBounds(120,160,80,30);
                stuage.setBounds(20, 180, 80, 18);stuage1.setBounds(120, 180, 80, 30);
                grade.setBounds(20,200,80,18);grade1.setBounds(120,200,80,30);
                cond.setBounds(20, 220, 80, 18);cond1.setBounds(120, 220, 80, 30);
                location.setBounds(20,240,80,18);location1.setBounds(120,240,80,30);
                stuvol.setBounds(20,260,80,18);stuvol1.setBounds(120,260,80,30);
                Tuition.setBounds(20, 280, 80, 18);Tuition1.setBounds(120, 280, 80, 30);
                Contui.setBounds(20,300,80,18);Contui1.setBounds(120,300,80,30);
                gpa.setBounds(20,320,80,18);gpa1.setBounds(120,320,80,30);
                check.setBounds(200,40,120,40);table.setBounds(350,40,120,30);
                apply.setBounds(200,200,120,40);issue.setBounds(350,200,120,30);
                option.setBounds(200,360,120,40);veri.setBounds(350,360,120,30);
                direct.setBounds(200,520,120,40);veri1.setBounds(350,520,120,30);
                volun.setBounds(20,400,120,40);ccp.setBounds(20,500,120,40);
                setVisible(true);
                option.setVisible(false);veri.setVisible(false);
                direct.setVisible(false);veri1.setVisible(false);
                entrance chabiao = new entrance(c,veri,option,veri1,direct,table,"select",str);
                entrance shenqing = new entrance(c,veri,option,veri1,direct,issue,"apply",str);
                volun.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expandvol f =new expandvol(str);
                        f.setVisible(true);
                    }
                });
                ccp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expandccp f =new expandccp(str);
                        f.setVisible(true);
                    }
                });
                check.addActionListener(chabiao);
                apply.addActionListener(shenqing);
            }
            else if (ver == 2){
                SQL = "select *" + " from ��ʦ��Ϣ��" + " where TeaID="+text_name.getText().trim();
                stmt = con.prepareStatement(SQL);
                // stmt.setString(1,text_name.getText());
                rs = stmt.executeQuery();

                if(rs.next()){
                    TeacherFrame t = new TeacherFrame(rs.getString(1).trim());
                }

            }
            else if(ver==3){
                //System.out.println("1111111111");
                JiaowuchuFrame f = new JiaowuchuFrame(text_name.getText().trim());
                f.setVisible(true);

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        Connection con = null;PreparedStatement stmt = null;ResultSet rs = null;
        if (opreating.equals("select")){
            String option = opti.getSelectedItem().toString().trim();
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                if(option.equals("ѡ����Ϣ��")) {
                    String SQL = "select * from ѡ����Ϣ��_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,1);
                }
                else if(option.equals("�༶��Ϣ��")) {
                    String SQL = "select * from �༶��Ϣ��_view where Clanum='"+id[6]+"';";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,2);
                }
                else if(option.equals("����Ϣ��")) {
                    String SQL = "select * from �񽱼�¼��_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,3);
                }
                else if(option.equals("������Ϣ��")) {
                    String SQL = "select * from ���ּ�¼��_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,4);
                }
                else if(option.equals("������¼��")) {
                    String SQL = "select * from ������¼��_view where Num="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,5);
                }
            }catch (Exception ea){
                ea.printStackTrace();
                System.out.println("select error\n");
            }
        }
        else if(opreating.equals("apply")){
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                String option = opti.getSelectedItem().toString().trim();
                if(option.equals("ѡ��")) {
                    veri.setVisible(true);opt.setVisible(true);
                    //String xuan = opt.getSelectedItem().toString().trim();
                    //System.out.println(xuan);
                    exeapply xuanke = new exeapply();
                    xuanke.init(opt,id,option);
                    veri.addActionListener(xuanke);
                }
               else if(option.equals("תרҵ")) {
                    veri1.setVisible(true);dir.setVisible(true);
                    //String zhuan = dir.getSelectedItem().toString().trim();
                    exeapply zhuanzy = new exeapply();
                    zhuanzy.init(dir,id,option);
                    veri1.addActionListener(zhuanzy);
                }
               else if(option.equals("��ѧ")) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dat = format.format(date);
                    String SQL = "{call ��������(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat);calls.setString(2,"ѧ��");
                    calls.setString(3,id[0]);calls.setString(4,"��ѧ");
                    calls.setString(5,null);
                    calls.execute();
                    calls.close();
               }
                else if(option.equals("��ѧ��")) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dat = format.format(date);
                    String SQL = "{call ��������(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat); calls.setString(2,"ѧ��");
                    calls.setString(3,id[0]);calls.setString(4,"��ѧ��");
                    calls.setString(5,null);
                    calls.execute();
                    calls.close();
                }
                else if(option.equals("��ѧ")) {
                    //String change[] = {"ѡ����Ϣ��","�༶��Ϣ��"};
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dat = format.format(date);
                    String SQL = "{call ��������(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat); calls.setString(2,"ѧ��");
                    calls.setString(3,id[0]);calls.setString(4,"��ѧ");
                    calls.setString(5,null);
                    calls.execute();
                    calls.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("apply error\n");
            }
        }
    }
    public void exhibitres(ResultSet rs,int signal) {
        JFrame frame = new JFrame();
        frame.setTitle("��ѯ��Ϣ��");//���ô������
        frame.setSize(1500, 1000);//���ô����С��ֻ�Զ���������Ч
        //frame.setDefaultCloseOperation(3);//���ô���رղ�����3��ʾ�رմ����˳�����
        frame.setLocationRelativeTo(null);//���ô����������һ���ľ���λ�ã�����null��ʾ�����������Ļ������λ��
        frame.setResizable(true);//��ֹ���������С
        frame.setFont(new Font("����",Font.PLAIN,20));//�������壬��ʾ��ʽ��������С
        /*FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);*/
        try {
            String[] res = new String[9];
            if (signal == 1) {  //ѡ����Ϣ��
                int i=2;
                JLabel sid = new JLabel("ѧ��");sid.setFont(new Font("����", Font.PLAIN, 20));frame.add(sid);
                JLabel cid = new JLabel("�γ̺�");cid.setFont(new Font("����", Font.PLAIN, 20));frame.add(cid);
                JLabel grade = new JLabel("�ɼ�");grade.setFont(new Font("����", Font.PLAIN, 20));frame.add(grade);
                JLabel credit = new JLabel("ѧ��");credit.setFont(new Font("����", Font.PLAIN, 20));frame.add(credit);
                JLabel gpa = new JLabel("�γ̼���");gpa.setFont(new Font("����", Font.PLAIN, 20));frame.add(gpa);
                sid.setBounds(40,40,100,60);cid.setBounds(200,40,60,40);
                grade.setBounds(300,40,60,40);credit.setBounds(400,40,60,40);
                gpa.setBounds(520,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);
                    Object obj1= rs.getObject(3); if(obj1!=null){res[2] = res[2].valueOf(obj1);}
                    res[3] = res[3].valueOf(rs.getInt(4));
                    Object obj2= rs.getObject(5); if(obj2!=null){res[4] = res[4].valueOf(obj2);}
                    JLabel sid1 = new JLabel(res[0]);sid1.setFont(new Font("����", Font.PLAIN, 20));frame.add(sid1);
                    JLabel cid1 = new JLabel(res[1]);sid.setFont(new Font("����", Font.PLAIN, 20));frame.add(cid1);
                    JLabel grade1 = new JLabel(res[2]);grade1.setFont(new Font("����", Font.PLAIN, 20));frame.add(grade1);
                    JLabel credit1 = new JLabel(res[3]);credit1.setFont(new Font("����", Font.PLAIN, 20));frame.add(credit1);
                    JLabel gpa1 = new JLabel(res[4]);gpa1.setFont(new Font("����", Font.PLAIN, 20));frame.add(gpa1);
                    sid1.setBounds(40,40*i,100,40);cid1.setBounds(200,40*i,60,80);
                    grade1.setBounds(300,40*i,60,40);credit1.setBounds(400,40*i,60,80);
                    gpa1.setBounds(520,40*i,60,40);i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 2) {  //�༶��Ϣ��
                if (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);
                    res[2] = rs.getString(3);res[3] = res[3].valueOf(rs.getInt(4));
                }
                frame.setSize(500,400);
                JLabel sid = new JLabel("�༶��");sid.setFont(new Font("����", Font.PLAIN, 20));frame.add(sid);
                JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("����", Font.PLAIN, 20));frame.add(sid1);
                JLabel cid = new JLabel("רҵ");cid.setFont(new Font("����", Font.PLAIN, 20));frame.add(cid);
                JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("����", Font.PLAIN, 20));frame.add(cid1);
                JLabel grade = new JLabel("����ѧԺ");grade.setFont(new Font("����", Font.PLAIN, 20));frame.add(grade);
                JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("����", Font.PLAIN, 20));frame.add(grade1);
                JLabel credit = new JLabel("ѧ������");credit.setFont(new Font("����", Font.PLAIN, 20));frame.add(credit);
                JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("����", Font.PLAIN, 20));frame.add(credit1);
                sid.setBounds(20,20,100,40);sid1.setBounds(150,20,100,40);
                cid.setBounds(20,60,100,40);cid1.setBounds(150,60,100,40);
                grade.setBounds(20,100,100,40);grade1.setBounds(150,100,100,40);
                credit.setBounds(20,140,100,40);credit1.setBounds(150,140,100,40);
                frame.setVisible(true);
            }
            else if (signal == 3) {  //��
                int i=2;
                JLabel sid = new JLabel("��¼��");sid.setFont(new Font("����", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("ѧ��");cid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("ѧ������");grade.setFont(new Font("����", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("ѧ��רҵ");credit.setFont(new Font("����", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("������");awadate.setFont(new Font("����", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("����Ŀ");awarea.setFont(new Font("����", Font.PLAIN, 20));frame.add(awarea);
                JLabel awagra = new JLabel("�񽱵ȼ�");awagra.setFont(new Font("����", Font.PLAIN, 20));frame.add(awagra);
                sid.setBounds(40,40,100,40);cid.setBounds(210,40,100,40);
                grade.setBounds(380,40,100,40);credit.setBounds(550,40,100,40);
                awadate.setBounds(720,40,100,40);awarea.setBounds(890,40,100,40);
                awagra.setBounds(1060,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1).trim();res[1] = rs.getString(2).trim();res[2] = rs.getString(3).trim();
                    res[3] = rs.getString(4).trim();res[4] = res[4].valueOf(rs.getDate(5));res[5] = rs.getString(6).trim();
                    res[6] = rs.getString(7).trim();

                JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("����", Font.PLAIN,20));frame.add(sid1);
                JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid1);
                JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("����", Font.PLAIN,20));frame.add(grade1);
                JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("����", Font.PLAIN,20));frame.add(credit1);
                JLabel awadate1 = new JLabel(res[4].trim());awadate1.setFont(new Font("����", Font.PLAIN, 20));frame.add(awadate1);
                JLabel awarea1 = new JLabel(res[5].trim());awarea1.setFont(new Font("����", Font.PLAIN, 20));frame.add(awarea1);
                JLabel awagra1 = new JLabel(res[6].trim());awagra1.setFont(new Font("����", Font.PLAIN, 20));frame.add(awagra1);
                sid1.setBounds(40,40*i,100,40);cid1.setBounds(210,40*i,100,40);
                grade1.setBounds(380,40*i,100,40);credit1.setBounds(550,40*i,100,40);
                awadate1.setBounds(720,40*i,100,40);awarea1.setBounds(890,40*i,100,40);
                awagra1.setBounds(1060,40*i,100,40);
                i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 4) {  //����
                int i=2;
                JLabel sid = new JLabel("��¼��");sid.setFont(new Font("����", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("ѧ��");cid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("ѧ������");grade.setFont(new Font("����", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("ѧ��רҵ");credit.setFont(new Font("����", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("��������");awadate.setFont(new Font("����", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("����ԭ��");awarea.setFont(new Font("����", Font.PLAIN,20));frame.add(awarea);
                JLabel awagra = new JLabel("���ֵȼ�");awagra.setFont(new Font("����", Font.PLAIN,20));frame.add(awagra);
                sid.setBounds(40,40,100,40);cid.setBounds(210,40,100,40);
                grade.setBounds(380,40,100,40);credit.setBounds(550,40,100,40);
                awadate.setBounds(720,40,100,40);awarea.setBounds(890,40,100,40);
                awagra.setBounds(1060,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);res[2] = rs.getString(3);
                    res[3] = rs.getString(4);res[4] = res[4].valueOf(rs.getDate(5));res[5] = rs.getString(6);
                    res[6] = rs.getString(7);
                    JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("����", Font.PLAIN,20));frame.add(sid1);
                    JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid1);
                    JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("����", Font.PLAIN,20));frame.add(grade1);
                    JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("����", Font.PLAIN,20));frame.add(credit1);
                    JLabel awadate1 = new JLabel(res[4].trim());awadate1.setFont(new Font("����", Font.PLAIN,20));frame.add(awadate1);
                    JLabel awarea1 = new JLabel(res[5].trim());awarea1.setFont(new Font("����", Font.PLAIN,20));frame.add(awarea1);
                    JLabel awagra1 = new JLabel(res[6].trim());awagra1.setFont(new Font("����", Font.PLAIN,20));frame.add(awagra1);
                    sid1.setBounds(40,40*i,100,40);cid1.setBounds(210,40*i,100,40);
                    grade1.setBounds(380,40*i,100,40);credit1.setBounds(550,40*i,100,40);
                    awadate1.setBounds(720,40*i,100,40);awarea1.setBounds(890,40*i,100,40);
                    awagra1.setBounds(1060,40*i,100,40);i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 5) {  //�����¼
                int i=2;
                JLabel sid = new JLabel("������");sid.setFont(new Font("����", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("����������");cid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("������Ŀ");grade.setFont(new Font("����", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("�����ˣ�ѧ��ְ����");credit.setFont(new Font("����", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("��������");awadate.setFont(new Font("����", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("�Ƿ�����");awarea.setFont(new Font("����", Font.PLAIN,20));frame.add(awarea);
                JLabel awagra = new JLabel("�Ƿ����");awagra.setFont(new Font("����", Font.PLAIN,20));frame.add(awagra);
                JLabel result = new JLabel("������");awagra.setFont(new Font("����", Font.PLAIN,20));frame.add(result);
                JLabel content = new JLabel("��������");awagra.setFont(new Font("����", Font.PLAIN,20));frame.add(content);
                sid.setBounds(40,100,150,80);cid.setBounds(200,100,150,80);
                grade.setBounds(480,100,150,80);credit.setBounds(700,100,150,80);
                awadate.setBounds(920,100,150,80);awarea.setBounds(1140,100,150,80);
                awagra.setBounds(1380,100,150,80); result.setBounds(1060,100,150,80);
                content.setBounds(1520,100,150,80);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);res[2] = rs.getString(3);
                    res[3] = rs.getString(4);res[4] = rs.getString(5);res[5] = res[5].valueOf(rs.getBoolean(6));
                    res[6] = res[6].valueOf(rs.getBoolean(7));res[7] = rs.getString(8);res[8] = rs.getString(9);

                JLabel sid1 = new JLabel(res[0]);sid1.setFont(new Font("����", Font.PLAIN,20));frame.add(sid1);
                JLabel cid1 = new JLabel(res[1]);sid.setFont(new Font("����", Font.PLAIN,20));frame.add(cid1);
                JLabel grade1 = new JLabel(res[2]);grade1.setFont(new Font("����", Font.PLAIN,20));frame.add(grade1);
                JLabel credit1 = new JLabel(res[3]);credit1.setFont(new Font("����", Font.PLAIN,20));frame.add(credit1);
                JLabel awadate1 = new JLabel(res[4]);awadate1.setFont(new Font("����", Font.PLAIN,20));frame.add(awadate1);
                JLabel awarea1 = new JLabel(res[5]);awarea1.setFont(new Font("����", Font.PLAIN,20));frame.add(awarea1);
                JLabel awagra1 = new JLabel(res[6]);awagra1.setFont(new Font("����", Font.PLAIN,20));frame.add(awagra1);
                JLabel result1 = new JLabel(res[7]);awagra1.setFont(new Font("����", Font.PLAIN,20));frame.add(result1);
                JLabel content1 = new JLabel(res[7]);awagra1.setFont(new Font("����", Font.PLAIN,20));frame.add(content1);
                    sid1.setBounds(40,100*i,150,80);cid1.setBounds(210,100*i,150,80);
                    grade1.setBounds(480,100*i,150,80);credit1.setBounds(700,100*i,150,80);
                    awadate1.setBounds(920,100*i,150,80);awarea1.setBounds(1140,100*i,150,80);
                    awagra1.setBounds(1380,100*i,150,80);result1.setBounds(1060,100*i,150,80);
                    content1.setBounds(1520,100*i,150,80);
                    i++;
                }
                frame.setVisible(true);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unknown Error\n");
        }
    }
}

class exeapply extends JFrame implements ActionListener{
    String[] info = new String[15];
    JComboBox option;
    String operate;
    public void init(JComboBox option,String[] info,String operate){
        this.info = info;
        this.option = option;
        this.operate = operate;
    }
    public void actionPerformed(ActionEvent e){
        Connection con = null;PreparedStatement stmt = null;ResultSet rs = null;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=dbkeshe";
        if(operate.equals("ѡ��")){
            try{
                /*ѡ�Σ����ÿγ���Ϣ����������û��*/
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                String opt = option.getSelectedItem().toString().trim();
                //System.out.println(opt);
                String SQL = "select * from �γ���Ϣ��_view where CourseName='"+opt+"';";
               // String SQL = "select * from ѡ����Ϣ��_view where StuID="+id[0]+";";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next()){
                    int renshu = rs.getInt("counts");
                   // int max = rs.getInt("capa");
                   // System.out.printf("%d %d",renshu,max);
                    if(renshu+1<100){
                        String courseid = rs.getString("CourseID");
                        int credit = rs.getInt("Credit");
                        SQL = "insert into ѡ����Ϣ�� values('"+info[0].trim()+"','"+courseid.trim()+"',null,"+credit+",null);";
                        stmt = con.prepareStatement(SQL);
                        System.out.println(SQL);
                        int resu = stmt.executeUpdate();
                        Date date = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dat = format.format(date);
                        SQL = "{call ��������(?,?,?,?,?)}";
                        CallableStatement calls = con.prepareCall(SQL);
                        calls.setString(1,dat); calls.setString(2,"ѧ��");
                        calls.setString(3,info[0]);calls.setString(4,"ѡ��");
                        calls.setString(5,opt);
                        calls.execute();
                        calls.close();
                    }
                    else{
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,"�˿γ������");
                        //���ô�����������ֵ
                        /*frame.setTitle("Selecting result");//���ô������
                        frame.setSize(400, 300);//���ô����С��ֻ�Զ���������Ч
                        frame.setDefaultCloseOperation(3);//���ô���رղ�����3��ʾ�رմ����˳�����
                        frame.setLocationRelativeTo(null);//���ô����������һ���ľ���λ�ã�����null��ʾ�����������Ļ������λ��
                        frame.setResizable(false);//��ֹ���������С
                        frame.setFont(new Font("����", Font.PLAIN,20));//�������壬��ʾ��ʽ��������С
                        //ʵ����FlowLayout��ʽ������Ķ���ָ�����뷽ʽΪ���ж������֮��ļ��Ϊ10������
                        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
                        //ʵ������ʽ������Ķ���
                        frame.setLayout(fl);
                        JLabel awagra1 = new JLabel("�˿γ��������");awagra1.setFont(new Font("����", Font.PLAIN,20));frame.add(awagra1);
                        frame.setVisible(true);*/
                    }
                }
                else {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f,"�޴˿γ�");
                }
            }catch (Exception ei){
                ei.printStackTrace();
                System.out.println("choose course error\n");
            }
        }
        else if (operate.equals("תרҵ")){
            //String change[] = {"ѧ����Ϣ��","ѡ����Ϣ��","�༶��Ϣ��","������Ϣ��","־Ը��Ϣ��","�񽱼�¼��","���ּ�¼��","��¼��Ϣ��"};
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dat = format.format(date);
            String SQL;
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                String opt = option.getSelectedItem().toString().trim();
                SQL = "{call ��������(?,?,?,?,?)}";
                CallableStatement calls = con.prepareCall(SQL);
                calls.setString(1,dat); calls.setString(2,"ѧ��");
                calls.setString(3,info[0]);calls.setString(4,"תרҵ");
                calls.setString(5,opt);
                calls.execute();
                calls.close();
            }catch (Exception ei){
                ei.printStackTrace();
                System.out.println("change project error\n");
            }
        }
    }
}

