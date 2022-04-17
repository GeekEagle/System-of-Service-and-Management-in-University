package com.iflytek.voicecloud.webapi.demo;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;

/**
 * 思路：采用java官方API——TargetDataLine，从声卡中采集音频数据达到录音效果，采集的数据为PCM裸流需要转为wav格式的话参照——PCM转WAV 。
 * @author Administrator
 *
 */
public class Sound {

    boolean isStop=false;
    //采样率
    private static float RATE = 16000f;
    //编码格式PCM
    private static AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
    //帧大小 16
    private static int SAMPLE_SIZE = 16;
    //是否大端
    private static boolean BIG_ENDIAN = false;//true
    //通道数
    private static int CHANNELS = 1;

    public void save(String path) throws Exception {
        String hostUrl = "https://iat-api.xfyun.cn/v2/iat"; //中英文，http url 不支持解析 ws/wss schema
        // private static final String hostUrl = "https://iat-niche-api.xfyun.cn/v2/iat";//小语种
        String appid = "6b051272"; //在控制台-我的应用获取
        String apiSecret = "YWE3MWM4NGExOTk4YjZjNjVhMWNlOTVi"; //在控制台-我的应用-语音听写（流式版）获取
        String apiKey = "531ff9d911c69221d3bd7dfe892eefe1"; //在控制台-我的应用-语音听写（流式版）获取
        String[] ff = new String[2];
        ff[0]="E:\\code\\java_sqlserver\\record\\test.pcm";
        ff[1]="E:\\code\\java_sqlserver\\record\\test.wav";

        //创建指定文件
        File file = new File(path);

        if(file.isDirectory()) {
            if(!file.exists()) {
                file.mkdirs();
            }
            file.createNewFile();
        }
        //设置格式
        AudioFormat audioFormat = new AudioFormat(ENCODING,RATE, SAMPLE_SIZE, CHANNELS, (SAMPLE_SIZE / 8) * CHANNELS,
                RATE, BIG_ENDIAN);
        //获取线路
        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
        targetDataLine.open();
        targetDataLine.start();



        /**targetDataLine.read()
         * 从数据线的输入缓冲区读取音频数据，该方法会阻塞，当数据先关闭之后就不会阻塞了
         */
        Thread thread=new Thread() {
            int flag = 0;
            OutputStream os = new FileOutputStream(file);
            byte[] b = new byte[256];
            public void run() {
                while((flag = targetDataLine.read(b, 0, b.length))>0) {//从声卡中采集数据
                    try {
                        os.write(b);
                    } catch (IOException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
//					System.out.println(flag);
                    if(isStop) {
                        isStop=false;
                        try {
                            targetDataLine.close();
                            pcmtowav.convertAudioFiles(ff);
                            String authUrl = WebIATWS.getAuthUrl(hostUrl, apiKey, apiSecret);
                            OkHttpClient client = new OkHttpClient.Builder().build();
                            //将url中的 schema http://和https://分别替换为ws:// 和 wss://
                            String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
                            //System.out.println(url);
                            Request request = new Request.Builder().url(url).build();
                            // System.out.println(client.newCall(request).execute());
                            //System.out.println("url===>" + url);
                            WebSocket webSocket = client.newWebSocket(request,new WebIATWS());

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    }

                }
            }
        };

        thread.start();
        //监听按键
        Thread thread2=new Thread() {
            public void run() {
                try{
                    this.sleep(5000);
                    isStop=true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread2.start();
    }

    public static void main(String[] args) {

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

}