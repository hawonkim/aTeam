package com.example.my_miniproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.my_miniproject2.Atask.JoinInsert;
import com.example.my_miniproject2.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Join_Activity extends AppCompatActivity {
    EditText edt_id , edt_pw , edt_name , edt_gender ,edt_email , edt_age;
    CheckBox chk_m , chk_w;
    String gender = "남";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        edt_id = findViewById(R.id.join_edt_id);
        edt_pw = findViewById(R.id.join_edt_pw);
        edt_name = findViewById(R.id.join_edt_nickname);
      //  edt_gender = findViewById(R.id.join_edt_gende);
        edt_email = findViewById(R.id.join_edt_addr);
        edt_age = findViewById(R.id.join_edt_age);
        chk_m = findViewById(R.id.chk_m);
        chk_w = findViewById(R.id.chk_w);

        chk_m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_w.setChecked(false);
                gender = "남";
            }
        });

        chk_w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_m.setChecked(false);
                gender = "여";
            }
        });

        findViewById(R.id.join_btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.join_btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 Atask
                int age = 0;
                try{
                    age = Integer.parseInt(  edt_gender.getText().toString());
                }catch (Exception e){

                }
                HashMap<String,String> map = new HashMap<>();
                map.put("id", edt_id.getText().toString() );
                map.put("pw", edt_pw.getText().toString() );
                map.put("nick", edt_name.getText().toString() );
                map.put("addr", edt_email.getText().toString());
                map.put("gender", gender );
                map.put("age", age + "");



                JoinInsert joinInsert = new JoinInsert(map);
                try {
                    joinInsert.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.id_check_btn).setOnClickListener(new );
    }
}