package com.iflytek.voicecloud.webapi.demo;

import com.iflytek.voicecloud.webapi.demo.course.EnterScores;

import javax.swing.*;

public class tonum extends JFrame {
    public static String pri;
    public tonum(){
        JFrame frame=this;
        JOptionPane.showMessageDialog(frame, "没有检测到成绩二字");
    }
    public static  String getStr(String str)
    {
        pri = new String();
        pri="";
        int div=str.indexOf("成绩");
        if(div==-1)
            div=str.indexOf("x");
        if(div==-1)
            div=str.indexOf("×");
        if(div==-1)
        {
            new tonum();
            return "error";
        }
        for(int i=0;i<str.length();i++)
        {
            System.out.println(str.charAt(i));
            if(str.charAt(i)=='零' || str.charAt(i)=='0')
                pri=pri.concat("0");
            if(str.charAt(i)=='一' || str.charAt(i)=='1')
            {
                pri=pri.concat("1");
            }
            if(str.charAt(i)=='二' || str.charAt(i)=='2')
                pri=pri.concat("2");
            if(str.charAt(i)=='三' || str.charAt(i)=='3')
                pri=pri.concat("3");
            if(str.charAt(i)=='四' || str.charAt(i)=='4')
                pri=pri.concat("4");
            if(str.charAt(i)=='五' || str.charAt(i)=='5')
                pri=pri.concat("5");
            if(str.charAt(i)=='六' || str.charAt(i)=='6')
                pri=pri.concat("6");
            if(str.charAt(i)=='七' || str.charAt(i)=='7')
                pri=pri.concat("7");
            if(str.charAt(i)=='八' || str.charAt(i)=='8')
                pri=pri.concat("8");
            if(str.charAt(i)=='九' || str.charAt(i)=='9')
                pri=pri.concat("9");
            if(i==div)
            {
                pri=pri.concat(" ");
            }
        }
        String[] temp = pri.split(" ");
        EnterScores.txtSno.setText(temp[0]);
        EnterScores.txtScores.setText(temp[1]);
        return pri;
    }
}
