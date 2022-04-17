package com.iflytek.voicecloud.webapi.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class test {
    public static void main(String[] args)
    {
         String hostUrl = "https://iat-api.xfyun.cn/v2/iat"; //中英文，http url 不支持解析 ws/wss schema
        // private static final String hostUrl = "https://iat-niche-api.xfyun.cn/v2/iat";//小语种
         String appid = "6b051272"; //在控制台-我的应用获取
         String apiSecret = "YWE3MWM4NGExOTk4YjZjNjVhMWNlOTVi"; //在控制台-我的应用-语音听写（流式版）获取
         String apiKey = "531ff9d911c69221d3bd7dfe892eefe1"; //在控制台-我的应用-语音听写（流式版）获取
        Sound f = new Sound();
        try
        {
            f.save("E:\\code\\java_sqlserver\\record\\test.pcm");
            String authUrl = WebIATWS.getAuthUrl(hostUrl, apiKey, apiSecret);
            OkHttpClient client = new OkHttpClient.Builder().build();
            //将url中的 schema http://和https://分别替换为ws:// 和 wss://
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
