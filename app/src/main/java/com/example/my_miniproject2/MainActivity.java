package com.example.my_miniproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_miniproject2.Atask.LoginSelect;
import com.example.my_miniproject2.DTO.MemberDTO;
import com.example.my_miniproject2.Join_Activity;
import com.example.my_miniproject2.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText edt_id, edt_pw;
    Button btn_login;
    public static MemberDTO loginDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OnClick이벤트가 발생하면 ATask 를 이용해서
                //Spring Url에 접근하여 입력한 Id,Pw가 일치하는지 검사
                HashMap<String,String> map = new HashMap<>();
                map.put("id" , edt_id.getText().toString());
                map.put("pw" , edt_pw.getText().toString());

                LoginSelect loginSelect = new LoginSelect(map);


                try {

                    loginSelect.execute().get();
                    if (loginDTO != null) {
                        if (edt_id.getText().toString().trim().equals(loginDTO.getId())
                                && edt_pw.getText().toString().trim().equals(loginDTO.getPw())) {
                            Toast.makeText(MainActivity.this, "정상로그인", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "비비정상로그인", Toast.LENGTH_SHORT).show();

                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.join_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Join_Activity.class);
                startActivity(intent);
            }
        });
    }
}