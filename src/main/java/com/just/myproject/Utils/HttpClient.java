package com.just.myproject.Utils;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装http方法
 */
public class HttpClient {
    private static  final Gson gson=new Gson();
    private static  final RequestConfig requestConfig= RequestConfig.custom().setConnectTimeout(5000)//连接超时
            .setConnectionRequestTimeout(5000)//请求超时
            .setSocketTimeout(5000)
            .setRedirectsEnabled(true)//允许重定向
            .build();

    public static Map<String,Object> doGet(String url){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        Map<String,Object> map=new HashMap<>();

        HttpGet httpGet=new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try {
            HttpResponse httpResponse= httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode()==200){

                String jsonResult=EntityUtils.toString(httpResponse.getEntity());
                gson.fromJson(jsonResult,map.getClass());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  map;
    }

    public static  String doPost(String url ,String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");
        if (data != null || data instanceof String) {
            StringEntity stringEntity = new StringEntity(data.toString(), "UTF-8");
            httpPost.setEntity(stringEntity);
        }
        try {
            HttpResponse httpEntity = httpClient.execute(httpPost);
            if (httpEntity.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString(httpEntity.getEntity());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
