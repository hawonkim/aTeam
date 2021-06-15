package com.example.my_miniproject2.Atask;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.my_miniproject2.Common.ConnSpring;
import com.example.my_miniproject2.DTO.MemberDTO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import static com.example.my_miniproject2.MainActivity.loginDTO;

/*Atask 패키지에는 Spring과 통신하는 Asynk Task들을 여기에 모두 넣겠다.*/
public class JoinInsert extends AsyncTask<Void, Void, Void> {
    HashMap<String,String> map;
    String url;

    public JoinInsert(HashMap<String,String> map ,String url){

        this.map = map;
        this.url = url;
    }

    //실제 Spring과 연결 하여 어떤 데이터 작업이 일어나는 부분
    @Override
    protected Void doInBackground(Void... voids) {
        //Multipart 빌더를 생성
        ConnSpring connSpring = new ConnSpring();
       InputStream inputStream =
               connSpring.connSetting(map,url);
        try {
            String result = readMessage(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private String readMessage(InputStream inputStream) throws IOException {

        JsonReader reader = new JsonReader(
                new InputStreamReader(inputStream, "UTF-8")
        );
        String  result ="";

        //Json구조에서 다음 정보가 있는지 체크를 하면서 While문을 통해서 무한반복
        //ArrayList일경우 json{{beginArray
        //reader.beginArray();
        //Dto나 Map구조 그대로를 json으로 만든경우는 beginObject
        reader.beginObject();
        //Json리더를 열어줌
        while (reader.hasNext()){
            String tempStr = reader.nextName();
            if (tempStr.equals("result")){
                result = reader.nextString();
            }
            else{
                reader.skipValue();
                //reader에 들어있는 값을 skip
            }
        }
        //reader.endArray();
        reader.endObject();
        return result;
    }
}
