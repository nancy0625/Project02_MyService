package com.mad.trafficclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordActivity extends Activity implements View.OnClickListener {

    private TextView textView41;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        findViewById(R.id.btn_project41_p).setOnClickListener(this);
        textView41 = (TextView) findViewById(R.id.textView41);
        findViewById(R.id.submitp).setOnClickListener(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_project41_p:
                Intent intent = new Intent(this, Project41Activity.class);
                startActivity(intent);
                break;
            case R.id.submitp:
               checkinfo();
                break;
        }
    }
    private void checkinfo(){
        String user = getUser41p().getText().toString().trim();

        String email = getEmail41p().getText().toString().trim();
        String u1 = "[a-zA-Z]{4,25}";
        String ss1 = "[0-9]{6,25}";
        Pattern pattern1 = Pattern.compile(u1);
        Pattern pattern2 = Pattern.compile(ss1);
        Matcher matcher1 = pattern1.matcher(user);
        Matcher matcher2 = pattern2.matcher(email);

        if (!matcher1.matches()){
            Toast.makeText(this,"用户名格式不符合，请重新输入",Toast.LENGTH_LONG).show();
        }else   if (!matcher2.matches()){
            Toast.makeText(this,"邮箱格式不符合，请重新输入",Toast.LENGTH_LONG).show();
        }else {
           String uu = sp.getString("usernames","");
           String pp = sp.getString("passwords","");
            Toast.makeText(this,"用户名："+uu+"密码"+pp,Toast.LENGTH_LONG).show();

        }
    }

    private EditText getUser41p(){
        return (EditText) findViewById(R.id.user_41p);
    }

    private EditText getEmail41p(){
        return (EditText) findViewById(R.id.email_41p);
    }
}
