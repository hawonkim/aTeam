package com.example.my_miniproject2.Common;

import android.net.http.AndroidHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.example.my_miniproject2.Common.CommonMethod.ipconfig;
import static com.example.my_miniproject2.Common.CommonMethod.project_path;
import static com.example.my_miniproject2.MainActivity.loginDTO;

public class ConnSpring {

    //Spring 연결시 Http통신할때 필요한 것들
    HttpClient httpClient; //Clien설정부분
    HttpPost httpPost; //Url 맵핑이 들어가는 곳
    HttpResponse httpResponse; // 실제 요청하는곳
    HttpEntity httpEntity; // Parameter나 기타 설정들이 들어가는곳
    //

    public InputStream connSetting(HashMap<String,String> map , String url){
        InputStream inputStream = null;
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE );

            //문자열 및 데이터 (조회조건이나 insert 시 필요한 데이터들을 넘길때)
            //Android -> Spring 가는 데이터
            //http://192.168.0.2/test/mapping?id=abc&pw=abb

            //바뀌어야 할부분
            for (Map.Entry<String,String> entry : map.entrySet()){
                builder.addTextBody(entry.getKey() ,entry.getKey(), ContentType.create("Multipart/related","UTF-8"));
            }

            //Url을 만들기
            String postURL = ipconfig + project_path + url;


            httpClient = AndroidHttpClient.newInstance("Android");
            //접속할 Url을 초기화
            httpPost = new HttpPost(postURL);
            //조회 조건이나 데이터 설정들을 넘겨줌
            httpPost.setEntity(builder.build());

            //실제 Spring Url을 요청하는 부분
            httpResponse = httpClient.execute(httpPost);

            //값을 Spring에서 받아오는 부분
            httpEntity = httpResponse.getEntity();
            //entity 이용해서 값을 받아오고 그리고 html로 리턴된 부분을 getContent이용해서받아옴
            inputStream = httpEntity.getContent();


            //LoginDTO에 값을 넣는 처리

            //닫아줌


        } catch (IOException e) {
            loginDTO = null;
            e.printStackTrace();
        }finally {
            //통신을 완료하고 값들을 전부 비움
            if (httpEntity != null){
                httpEntity = null;
            }
            if (httpResponse != null){
                httpResponse = null;
            }
            if (httpPost != null){
                httpPost = null;
            }
            if (httpClient != null){
                httpClient = null;
            }
        }
        return inputStream;
    }
}
