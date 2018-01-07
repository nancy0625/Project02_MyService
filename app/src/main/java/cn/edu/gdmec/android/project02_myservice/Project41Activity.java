package com.mad.trafficclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;

import com.mad.trafficclient.activity.Activity_Main;

public class Project41Activity extends Activity implements View.OnClickListener {

    private TextView textView41;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project41);

        textView41 = (TextView) findViewById(R.id.textView41);
        findViewById(R.id.submit).setOnClickListener(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        isRemenber();
        getPswCb41().setOnClickListener(this);
        getRegisterCb41().setOnClickListener(this);

    }

    private EditText getUser41(){
        return (EditText) findViewById(R.id.user_41);
    }

    private EditText getPsw41(){
        return (EditText) findViewById(R.id.psw_41);
    }

    private TextView getRegisterCb41(){
        return (TextView) findViewById(R.id.register_cb41);
    }

    private TextView getPswCb41(){
        return (TextView) findViewById(R.id.psw_cb41);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                sta();
                break;
            case R.id.register_cb41:
                register(view);
                break;
            case R.id.psw_cb41:
                password(view);
                break;
        }
    }
    private void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void password(View view){
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);
    }
    private void isRemenber(){
        String user = sp.getString("username","");
        String pass = sp.getString("password","");
        if (user.length()>0&&pass.length()>0){
            Intent intent = new Intent(this, Activity_Main.class);
            startActivity(intent);
        }
    }
    private void sta(){
        String username = getUser41().getText().toString().trim();
        String password = getPsw41().getText().toString().trim();
        if (username.equals("admin")&&password.equals("123456")){
            editor = sp.edit();
            editor.putString("username",username);
            editor.putString("password",password);
            editor.commit();
            Intent intent = new Intent(this, Activity_Main.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"用户名或者密码错误",Toast.LENGTH_LONG).show();
        }
    }
}
