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

import com.mad.trafficclient.activity.Activity_Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private TextView textView41;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        findViewById(R.id.btn_project41_s).setOnClickListener(this);
        textView41 = (TextView) findViewById(R.id.textView41);
        findViewById(R.id.submits).setOnClickListener(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_project41_s:
                Intent intent = new Intent(this, Project41Activity.class);
                startActivity(intent);
                break;
            case R.id.submits:
               checkinfo();

                break;
        }
    }
    private void checkinfo(){
        String user = getUser41s().getText().toString().trim();
        String pws = getPsw41s().getText().toString().trim();
        String pssw = getPsssw41s().getText().toString().trim();
        String email = getEmail41().getText().toString().trim();
        String u1 = "[a-zA-Z]{4,25}";
        String ss1 = "[0-9]{6,25}";
        Pattern pattern1 = Pattern.compile(u1);
        Pattern pattern2 = Pattern.compile(ss1);
        Matcher matcher1 = pattern1.matcher(user);

        if (!pssw.equals(pws)){
            Toast.makeText(this,"两次密码不符合，请重新输入",Toast.LENGTH_LONG).show();
        }
        Matcher matcher2 = pattern2.matcher(pssw);
        Matcher matcher3 = pattern2.matcher(email);
        if (!matcher1.matches()){
            Toast.makeText(this,"用户名格式不符合，请重新输入",Toast.LENGTH_LONG).show();
        }else   if (!matcher2.matches()){
            Toast.makeText(this,"密码格式不符合，请重新输入",Toast.LENGTH_LONG).show();
        }else   if (!matcher3.matches()){
            Toast.makeText(this,"邮箱格式不符合，请重新输入",Toast.LENGTH_LONG).show();
        }else {
            editor = sp.edit();
            editor.putString("usernames",user);
            editor.putString("passwords",pssw);
            editor.putString("emails",email);
            editor.commit();
            Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();

        }
    }

    private EditText getUser41s(){
        return (EditText) findViewById(R.id.user_41s);
    }

    private EditText getPsw41s(){
        return (EditText) findViewById(R.id.psw_41s);
    }

    private EditText getPsssw41s(){
        return (EditText) findViewById(R.id.psssw_41s);
    }

    private EditText getEmail41(){
        return (EditText) findViewById(R.id.email_41);
    }
}
