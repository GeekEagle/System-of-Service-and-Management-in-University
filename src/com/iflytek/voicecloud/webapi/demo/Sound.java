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
 * ˼·������java�ٷ�API����TargetDataLine���������вɼ���Ƶ���ݴﵽ¼��Ч�����ɼ�������ΪPCM������ҪתΪwav��ʽ�Ļ����ա���PCMתWAV ��
 * @author Administrator
 *
 */
public class Sound {

    boolean isStop=false;
    //������
    private static float RATE = 16000f;
    //�����ʽPCM
    private static AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
    //֡��С 16
    private static int SAMPLE_SIZE = 16;
    //�Ƿ���
    private static boolean BIG_ENDIAN = false;//true
    //ͨ����
    private static int CHANNELS = 1;

    public void save(String path) throws Exception {
        String hostUrl = "https://iat-api.xfyun.cn/v2/iat"; //��Ӣ�ģ�http url ��֧�ֽ��� ws/wss schema
        // private static final String hostUrl = "https://iat-niche-api.xfyun.cn/v2/iat";//С����
        String appid = "6b051272"; //�ڿ���̨-�ҵ�Ӧ�û�ȡ
        String apiSecret = "YWE3MWM4NGExOTk4YjZjNjVhMWNlOTVi"; //�ڿ���̨-�ҵ�Ӧ��-������д����ʽ�棩��ȡ
        String apiKey = "531ff9d911c69221d3bd7dfe892eefe1"; //�ڿ���̨-�ҵ�Ӧ��-������д����ʽ�棩��ȡ
        String[] ff = new String[2];
        ff[0]="E:\\code\\java_sqlserver\\record\\test.pcm";
        ff[1]="E:\\code\\java_sqlserver\\record\\test.wav";

        //����ָ���ļ�
        File file = new File(path);

        if(file.isDirectory()) {
            if(!file.exists()) {
                file.mkdirs();
            }
            file.createNewFile();
        }
        //���ø�ʽ
        AudioFormat audioFormat = new AudioFormat(ENCODING,RATE, SAMPLE_SIZE, CHANNELS, (SAMPLE_SIZE / 8) * CHANNELS,
                RATE, BIG_ENDIAN);
        //��ȡ��·
        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
        targetDataLine.open();
        targetDataLine.start();



        /**targetDataLine.read()
         * �������ߵ����뻺������ȡ��Ƶ���ݣ��÷������������������ȹر�֮��Ͳ���������
         */
        Thread thread=new Thread() {
            int flag = 0;
            OutputStream os = new FileOutputStream(file);
            byte[] b = new byte[256];
            public void run() {
                while((flag = targetDataLine.read(b, 0, b.length))>0) {//�������вɼ�����
                    try {
                        os.write(b);
                    } catch (IOException e) {
                        // TODO �Զ����ɵ� catch ��
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
                            //��url�е� schema http://��https://�ֱ��滻Ϊws:// �� wss://
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
        //��������
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