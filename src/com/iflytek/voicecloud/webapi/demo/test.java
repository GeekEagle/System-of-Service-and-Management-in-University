package com.iflytek.voicecloud.webapi.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class test {
    public static void main(String[] args)
    {
         String hostUrl = "https://iat-api.xfyun.cn/v2/iat"; //��Ӣ�ģ�http url ��֧�ֽ��� ws/wss schema
        // private static final String hostUrl = "https://iat-niche-api.xfyun.cn/v2/iat";//С����
         String appid = "6b051272"; //�ڿ���̨-�ҵ�Ӧ�û�ȡ
         String apiSecret = "YWE3MWM4NGExOTk4YjZjNjVhMWNlOTVi"; //�ڿ���̨-�ҵ�Ӧ��-������д����ʽ�棩��ȡ
         String apiKey = "531ff9d911c69221d3bd7dfe892eefe1"; //�ڿ���̨-�ҵ�Ӧ��-������д����ʽ�棩��ȡ
        Sound f = new Sound();
        try
        {
            f.save("E:\\code\\java_sqlserver\\record\\test.pcm");
            String authUrl = WebIATWS.getAuthUrl(hostUrl, apiKey, apiSecret);
            OkHttpClient client = new OkHttpClient.Builder().build();
            //��url�е� schema http://��https://�ֱ��滻Ϊws:// �� wss://
            String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
            //System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            // System.out.println(client.newCall(request).execute());
            //System.out.println("url===>" + url);
            WebIATWS yuyin = new WebIATWS();
            WebSocket webSocket = client.newWebSocket(request,yuyin);
            System.out.println("qwq"+yuyin.decoder.toString());

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
