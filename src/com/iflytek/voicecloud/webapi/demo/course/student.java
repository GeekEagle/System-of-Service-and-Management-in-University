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
    JButton start = new JButton("开始");
    JButton end  = new JButton("停止");

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //在主函数中，实例化Login类的对象，调用初始化界面的方法
        student log = new student();
        log.initUI();
    }
    public void initUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");//设置窗体标题
        frame.setSize(800, 500);//设置窗体大小，只对顶层容器生效
        //frame.setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(new Font("宋体",Font.PLAIN,14));//设置字体，显示格式正常，大小
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel labname = new JLabel("账号：");labname.setFont(new Font("宋体",Font.PLAIN,14));frame.add(labname);

        JTextField text_name = new JTextField();Dimension dim1 = new Dimension(300,30);
        text_name.setPreferredSize(dim1);frame.add(text_name);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("密码：");labpass.setFont(new Font("宋体",Font.PLAIN,14));frame.add(labpass);
        //实例化JPasswordField
        JPasswordField text_password = new JPasswordField();text_password.setPreferredSize(dim1);frame.add(text_password);
        JLabel labrole = new JLabel("角色：");labrole.setFont(new Font("宋体",Font.PLAIN,14));frame.add(labrole);

        String str[] = {"student", "teacher", "manager"};JComboBox role = new JComboBox(str);
        role.setPreferredSize(dim1);frame.add(role);

        //实例化JButton组件
        JButton button1 = new JButton();Dimension dim2 = new Dimension(100,30);button1.setText("登录");button1.setFont(new Font("宋体",Font.PLAIN,14));
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);

        frame.setVisible(true);//窗体可见，一定要放在所有组件加入窗体后
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
        //获取登录界面、账号密码输入框对象
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
            String SQL = "select userid,password,role" +" from 登录信息表"+" where userid="+text_name.getText()+"and password="+text_password.getText();
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            if(rs.next()){
                if ( shenfen.equals("student")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(1,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"用户身份不匹配");
                }
                else if (shenfen.equals("teacher")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(2,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"用户身份不匹配");
                }
                else if (shenfen.equals("manager")){
                    if(rs.getString(3).trim().equals(shenfen)){
                        entrance info = new entrance();
                        info.init(3,login,text_name);
                    }
                    else JOptionPane.showMessageDialog(login,"用户身份不匹配");
                    System.out.println("hahahahahahaha");
                }
            }
            else JOptionPane.showMessageDialog(login,"账号或密码错误");

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
        //获取输入框对象
        this.con = c;
        this.opti = opti;
        this.opreating = opreating;
        this.id = id;        //学生信息
        this.opt = opt;      //选课下拉菜单
        this.veri = veri;    //选课确定按键
        this.veri1 = veri1;  //转专业确认
        this.dir = dir;    //转专业下拉菜单
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
                SQL = "select *" + " from 学生信息表" + " where StuID="+text_name.getText().trim();
                stmt = con.prepareStatement(SQL);
                // stmt.setString(1,text_name.getText());
                rs = stmt.executeQuery();
                setBounds(200,200,800,800);
                setTitle("学生登录");
                // if(ver==2){setTitle("教师登录");}
                //if(ver==3){setTitle("教务处登录");}
                Container c = getContentPane();
                //c.setLayout(new FlowLayout(FlowLayout.LEFT));
                setVisible(true);
                setAlwaysOnTop(true);
                c.setLayout(null);
                /*显示学生信息*/
                JLabel stunum = new JLabel("学号");JLabel stuname = new JLabel("姓名");JLabel stupro = new JLabel("专业");
                JLabel stuage = new JLabel("年龄");JLabel iden = new JLabel("政治面貌");JLabel teanum = new JLabel("导师");
                JLabel clanum = new JLabel("班级号");JLabel college = new JLabel("所在学院");JLabel grade = new JLabel("年级");
                JLabel cond = new JLabel("学生状态");JLabel location = new JLabel("籍贯");JLabel stuvol = new JLabel("志愿时长");
                JLabel Tuition = new JLabel("学费");JLabel Contui = new JLabel("是否缴费");JLabel gpa = new JLabel("绩点");
                String select[] = {"选课信息表", "班级信息表","获奖信息表","处分信息表","操作记录表"};
                JComboBox table = new JComboBox(select);
                Dimension dim1 = new Dimension(300,30);
                table.setPreferredSize(dim1);
                String req[] = {"选课", "转专业", "退学","交学费","休学"};
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
                JButton check = new JButton("查询");
                JButton apply = new JButton("申请");
                JButton volun = new JButton("修改志愿");
                JButton ccp = new JButton("党务相关");
                String course[] = {"高等数学", "数据库系统", "计算机组成原理","数据结构","编译原理","概率论与数理统计","金融学"};JComboBox option = new JComboBox(course);
                option.setPreferredSize(dim1);c.add(option);
                JButton veri = new JButton("确定");c.add(veri);
                String major[] = {"化工", "计算机", "机械","石工","勘查","安全","海工"};JComboBox direct = new JComboBox(major);
                direct.setPreferredSize(dim1);c.add(direct);
                JButton veri1 = new JButton("确定");c.add(veri1);

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
                SQL = "select *" + " from 教师信息表" + " where TeaID="+text_name.getText().trim();
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
                if(option.equals("选课信息表")) {
                    String SQL = "select * from 选课信息表_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,1);
                }
                else if(option.equals("班级信息表")) {
                    String SQL = "select * from 班级信息表_view where Clanum='"+id[6]+"';";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,2);
                }
                else if(option.equals("获奖信息表")) {
                    String SQL = "select * from 获奖记录表_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,3);
                }
                else if(option.equals("处分信息表")) {
                    String SQL = "select * from 处分记录表_view where StuID="+id[0]+";";
                    stmt = con.prepareStatement(SQL);rs = stmt.executeQuery();exhibitres(rs,4);
                }
                else if(option.equals("操作记录表")) {
                    String SQL = "select * from 操作记录表_view where Num="+id[0]+";";
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
                if(option.equals("选课")) {
                    veri.setVisible(true);opt.setVisible(true);
                    //String xuan = opt.getSelectedItem().toString().trim();
                    //System.out.println(xuan);
                    exeapply xuanke = new exeapply();
                    xuanke.init(opt,id,option);
                    veri.addActionListener(xuanke);
                }
               else if(option.equals("转专业")) {
                    veri1.setVisible(true);dir.setVisible(true);
                    //String zhuan = dir.getSelectedItem().toString().trim();
                    exeapply zhuanzy = new exeapply();
                    zhuanzy.init(dir,id,option);
                    veri1.addActionListener(zhuanzy);
                }
               else if(option.equals("退学")) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dat = format.format(date);
                    String SQL = "{call 发起申请(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat);calls.setString(2,"学生");
                    calls.setString(3,id[0]);calls.setString(4,"退学");
                    calls.setString(5,null);
                    calls.execute();
                    calls.close();
               }
                else if(option.equals("交学费")) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dat = format.format(date);
                    String SQL = "{call 发起申请(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat); calls.setString(2,"学生");
                    calls.setString(3,id[0]);calls.setString(4,"交学费");
                    calls.setString(5,null);
                    calls.execute();
                    calls.close();
                }
                else if(option.equals("休学")) {
                    //String change[] = {"选课信息表","班级信息表"};
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dat = format.format(date);
                    String SQL = "{call 发起申请(?,?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,dat); calls.setString(2,"学生");
                    calls.setString(3,id[0]);calls.setString(4,"休学");
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
        frame.setTitle("查询信息表");//设置窗体标题
        frame.setSize(1500, 1000);//设置窗体大小，只对顶层容器生效
        //frame.setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(true);//禁止调整窗体大小
        frame.setFont(new Font("宋体",Font.PLAIN,20));//设置字体，显示格式正常，大小
        /*FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);*/
        try {
            String[] res = new String[9];
            if (signal == 1) {  //选课信息表
                int i=2;
                JLabel sid = new JLabel("学号");sid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(sid);
                JLabel cid = new JLabel("课程号");cid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(cid);
                JLabel grade = new JLabel("成绩");grade.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(grade);
                JLabel credit = new JLabel("学分");credit.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(credit);
                JLabel gpa = new JLabel("课程绩点");gpa.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(gpa);
                sid.setBounds(40,40,100,60);cid.setBounds(200,40,60,40);
                grade.setBounds(300,40,60,40);credit.setBounds(400,40,60,40);
                gpa.setBounds(520,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);
                    Object obj1= rs.getObject(3); if(obj1!=null){res[2] = res[2].valueOf(obj1);}
                    res[3] = res[3].valueOf(rs.getInt(4));
                    Object obj2= rs.getObject(5); if(obj2!=null){res[4] = res[4].valueOf(obj2);}
                    JLabel sid1 = new JLabel(res[0]);sid1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(sid1);
                    JLabel cid1 = new JLabel(res[1]);sid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(cid1);
                    JLabel grade1 = new JLabel(res[2]);grade1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(grade1);
                    JLabel credit1 = new JLabel(res[3]);credit1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(credit1);
                    JLabel gpa1 = new JLabel(res[4]);gpa1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(gpa1);
                    sid1.setBounds(40,40*i,100,40);cid1.setBounds(200,40*i,60,80);
                    grade1.setBounds(300,40*i,60,40);credit1.setBounds(400,40*i,60,80);
                    gpa1.setBounds(520,40*i,60,40);i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 2) {  //班级信息表
                if (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);
                    res[2] = rs.getString(3);res[3] = res[3].valueOf(rs.getInt(4));
                }
                frame.setSize(500,400);
                JLabel sid = new JLabel("班级号");sid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(sid);
                JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(sid1);
                JLabel cid = new JLabel("专业");cid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(cid);
                JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(cid1);
                JLabel grade = new JLabel("所在学院");grade.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(grade);
                JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(grade1);
                JLabel credit = new JLabel("学生数量");credit.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(credit);
                JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(credit1);
                sid.setBounds(20,20,100,40);sid1.setBounds(150,20,100,40);
                cid.setBounds(20,60,100,40);cid1.setBounds(150,60,100,40);
                grade.setBounds(20,100,100,40);grade1.setBounds(150,100,100,40);
                credit.setBounds(20,140,100,40);credit1.setBounds(150,140,100,40);
                frame.setVisible(true);
            }
            else if (signal == 3) {  //获奖
                int i=2;
                JLabel sid = new JLabel("记录号");sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("学号");cid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("学生姓名");grade.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("学生专业");credit.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("获奖日期");awadate.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("获奖项目");awarea.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(awarea);
                JLabel awagra = new JLabel("获奖等级");awagra.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(awagra);
                sid.setBounds(40,40,100,40);cid.setBounds(210,40,100,40);
                grade.setBounds(380,40,100,40);credit.setBounds(550,40,100,40);
                awadate.setBounds(720,40,100,40);awarea.setBounds(890,40,100,40);
                awagra.setBounds(1060,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1).trim();res[1] = rs.getString(2).trim();res[2] = rs.getString(3).trim();
                    res[3] = rs.getString(4).trim();res[4] = res[4].valueOf(rs.getDate(5));res[5] = rs.getString(6).trim();
                    res[6] = rs.getString(7).trim();

                JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid1);
                JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid1);
                JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade1);
                JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit1);
                JLabel awadate1 = new JLabel(res[4].trim());awadate1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(awadate1);
                JLabel awarea1 = new JLabel(res[5].trim());awarea1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(awarea1);
                JLabel awagra1 = new JLabel(res[6].trim());awagra1.setFont(new Font("宋体", Font.PLAIN, 20));frame.add(awagra1);
                sid1.setBounds(40,40*i,100,40);cid1.setBounds(210,40*i,100,40);
                grade1.setBounds(380,40*i,100,40);credit1.setBounds(550,40*i,100,40);
                awadate1.setBounds(720,40*i,100,40);awarea1.setBounds(890,40*i,100,40);
                awagra1.setBounds(1060,40*i,100,40);
                i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 4) {  //处分
                int i=2;
                JLabel sid = new JLabel("记录号");sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("学号");cid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("学生姓名");grade.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("学生专业");credit.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("处分日期");awadate.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("处分原因");awarea.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awarea);
                JLabel awagra = new JLabel("处分等级");awagra.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awagra);
                sid.setBounds(40,40,100,40);cid.setBounds(210,40,100,40);
                grade.setBounds(380,40,100,40);credit.setBounds(550,40,100,40);
                awadate.setBounds(720,40,100,40);awarea.setBounds(890,40,100,40);
                awagra.setBounds(1060,40,100,40);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);res[2] = rs.getString(3);
                    res[3] = rs.getString(4);res[4] = res[4].valueOf(rs.getDate(5));res[5] = rs.getString(6);
                    res[6] = rs.getString(7);
                    JLabel sid1 = new JLabel(res[0].trim());sid1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid1);
                    JLabel cid1 = new JLabel(res[1].trim());sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid1);
                    JLabel grade1 = new JLabel(res[2].trim());grade1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade1);
                    JLabel credit1 = new JLabel(res[3].trim());credit1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit1);
                    JLabel awadate1 = new JLabel(res[4].trim());awadate1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awadate1);
                    JLabel awarea1 = new JLabel(res[5].trim());awarea1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awarea1);
                    JLabel awagra1 = new JLabel(res[6].trim());awagra1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awagra1);
                    sid1.setBounds(40,40*i,100,40);cid1.setBounds(210,40*i,100,40);
                    grade1.setBounds(380,40*i,100,40);credit1.setBounds(550,40*i,100,40);
                    awadate1.setBounds(720,40*i,100,40);awarea1.setBounds(890,40*i,100,40);
                    awagra1.setBounds(1060,40*i,100,40);i++;
                }
                frame.setVisible(true);
            }
            else if (signal == 5) {  //申请记录
                int i=2;
                JLabel sid = new JLabel("申请编号");sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid);
                JLabel cid = new JLabel("申请人类型");cid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid);
                JLabel grade = new JLabel("申请项目");grade.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade);
                JLabel credit = new JLabel("申请人（学）职工号");credit.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit);
                JLabel awadate = new JLabel("申请日期");awadate.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awadate);
                JLabel awarea = new JLabel("是否允许");awarea.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awarea);
                JLabel awagra = new JLabel("是否完成");awagra.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awagra);
                JLabel result = new JLabel("申请结果");awagra.setFont(new Font("宋体", Font.PLAIN,20));frame.add(result);
                JLabel content = new JLabel("申请内容");awagra.setFont(new Font("宋体", Font.PLAIN,20));frame.add(content);
                sid.setBounds(40,100,150,80);cid.setBounds(200,100,150,80);
                grade.setBounds(480,100,150,80);credit.setBounds(700,100,150,80);
                awadate.setBounds(920,100,150,80);awarea.setBounds(1140,100,150,80);
                awagra.setBounds(1380,100,150,80); result.setBounds(1060,100,150,80);
                content.setBounds(1520,100,150,80);
                while (rs.next()) {
                    res[0] = rs.getString(1);res[1] = rs.getString(2);res[2] = rs.getString(3);
                    res[3] = rs.getString(4);res[4] = rs.getString(5);res[5] = res[5].valueOf(rs.getBoolean(6));
                    res[6] = res[6].valueOf(rs.getBoolean(7));res[7] = rs.getString(8);res[8] = rs.getString(9);

                JLabel sid1 = new JLabel(res[0]);sid1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(sid1);
                JLabel cid1 = new JLabel(res[1]);sid.setFont(new Font("宋体", Font.PLAIN,20));frame.add(cid1);
                JLabel grade1 = new JLabel(res[2]);grade1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(grade1);
                JLabel credit1 = new JLabel(res[3]);credit1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(credit1);
                JLabel awadate1 = new JLabel(res[4]);awadate1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awadate1);
                JLabel awarea1 = new JLabel(res[5]);awarea1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awarea1);
                JLabel awagra1 = new JLabel(res[6]);awagra1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awagra1);
                JLabel result1 = new JLabel(res[7]);awagra1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(result1);
                JLabel content1 = new JLabel(res[7]);awagra1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(content1);
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
        if(operate.equals("选课")){
            try{
                /*选课，调用课程信息表，看人数满没满*/
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                String opt = option.getSelectedItem().toString().trim();
                //System.out.println(opt);
                String SQL = "select * from 课程信息表_view where CourseName='"+opt+"';";
               // String SQL = "select * from 选课信息表_view where StuID="+id[0]+";";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if(rs.next()){
                    int renshu = rs.getInt("counts");
                   // int max = rs.getInt("capa");
                   // System.out.printf("%d %d",renshu,max);
                    if(renshu+1<100){
                        String courseid = rs.getString("CourseID");
                        int credit = rs.getInt("Credit");
                        SQL = "insert into 选课信息表 values('"+info[0].trim()+"','"+courseid.trim()+"',null,"+credit+",null);";
                        stmt = con.prepareStatement(SQL);
                        System.out.println(SQL);
                        int resu = stmt.executeUpdate();
                        Date date = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dat = format.format(date);
                        SQL = "{call 发起申请(?,?,?,?,?)}";
                        CallableStatement calls = con.prepareCall(SQL);
                        calls.setString(1,dat); calls.setString(2,"学生");
                        calls.setString(3,info[0]);calls.setString(4,"选课");
                        calls.setString(5,opt);
                        calls.execute();
                        calls.close();
                    }
                    else{
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,"此课程无余额");
                        //设置窗体对象的属性值
                        /*frame.setTitle("Selecting result");//设置窗体标题
                        frame.setSize(400, 300);//设置窗体大小，只对顶层容器生效
                        frame.setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
                        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
                        frame.setResizable(false);//禁止调整窗体大小
                        frame.setFont(new Font("宋体", Font.PLAIN,20));//设置字体，显示格式正常，大小
                        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
                        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
                        //实例化流式布局类的对象
                        frame.setLayout(fl);
                        JLabel awagra1 = new JLabel("此课程已无余额");awagra1.setFont(new Font("宋体", Font.PLAIN,20));frame.add(awagra1);
                        frame.setVisible(true);*/
                    }
                }
                else {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f,"无此课程");
                }
            }catch (Exception ei){
                ei.printStackTrace();
                System.out.println("choose course error\n");
            }
        }
        else if (operate.equals("转专业")){
            //String change[] = {"学生信息表","选课信息表","班级信息表","财务信息表","志愿信息表","获奖记录表","处分记录表","登录信息表"};
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dat = format.format(date);
            String SQL;
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "159357");
                String opt = option.getSelectedItem().toString().trim();
                SQL = "{call 发起申请(?,?,?,?,?)}";
                CallableStatement calls = con.prepareCall(SQL);
                calls.setString(1,dat); calls.setString(2,"学生");
                calls.setString(3,info[0]);calls.setString(4,"转专业");
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

