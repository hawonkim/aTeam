package com.example.my_miniproject2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.my_miniproject2.Common.CommonMethod;
import com.example.my_miniproject2.Common.ConnSpring;
import com.example.my_miniproject2.DTO.MemberDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import static com.example.my_miniproject2.Common.CommonMethod.ipconfig;
import static com.example.my_miniproject2.Common.CommonMethod.project_path;
import static com.example.my_miniproject2.MainActivity.loginDTO;

/*Atask 패키지에는 Spring과 통신하는 Asynk Task들을 여기에 모두 넣겠다.*/
public class LoginSelect extends AsyncTask<Void, Void, Void> {
    HashMap<String,String> map;


    public LoginSelect(HashMap<String,String> map){
        this.map = map;
    }

    //실제 Spring과 연결 하여 어떤 데이터 작업이 일어나는 부분
    @Override
    protected Void doInBackground(Void... voids) {
        //Multipart 빌더를 생성
        ConnSpring connSpring = new ConnSpring();
       InputStream inputStream =
               connSpring.connSetting(map,  "/and_test_Login");
        try {
            loginDTO = readMessage(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private MemberDTO readMessage(InputStream inputStream) throws IOException {

        JsonReader reader = new JsonReader(
                new InputStreamReader(inputStream, "UTF-8")
        );
        String  pw ="", nick ="", addr ="", gender="" ;
        int id = 0;
        int age = 0;

        //Json구조에서 다음 정보가 있는지 체크를 하면서 While문을 통해서 무한반복
        //ArrayList일경우 json{{beginArray
        //reader.beginArray();
        //Dto나 Map구조 그대로를 json으로 만든경우는 beginObject
        reader.beginObject();
        //Json리더를 열어줌
        while (reader.hasNext()){
            String tempStr = reader.nextName();
            if (tempStr.equals("id")){
                id = reader.nextInt();
            }else if(tempStr.equals("pw")){
                pw = reader.nextString();
            }else if(tempStr.equals("nick")){
                nick = reader.nextString();
            }else if(tempStr.equals("addr")){
                addr = reader.nextString();
            }else if(tempStr.equals("gender")){
                gender = reader.nextString();
            }else if(tempStr.equals("age")){
                age = reader.nextInt();
            }
            else{
                reader.skipValue();
                //reader에 들어있는 값을 skip
            }
        }
        //reader.endArray();
        reader.endObject();
        return new MemberDTO(id,pw,nick,addr,age,gender);

    }
}
