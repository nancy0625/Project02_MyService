package cn.edu.gdmec.android.project02_myservice;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cn.edu.gdmec.android.project02_myservice.db.MyDataBaseHelper;
import cn.edu.gdmec.android.project02_myservice.httppost.HttpUtils;
import cn.edu.gdmec.android.project02_myservice.httppost.JsonTools;

public class Project02Activity extends Activity  {

    private TextView tv1;
    private TextView tv01;
    private TextView tv2;
    private TextView tv02;
    private TextView tv3;
    private TextView tv03;
    private TextView tv4;
    private TextView tv04;
    private TextView tv5;
    private TextView tv05;
    private TextView tv6;
    private TextView tv06;
    private HttpUtils httpThread,httpThread2;
    private String res1,res2;
    private MyDataBaseHelper dataBaseHelper;

   /* Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<String> list = new ArrayList<>();
            list = JsonTools.parseList1(res1);
            list.get(0);
            Log.i("list",list.toString());

        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project02);
        dataBaseHelper = new MyDataBaseHelper(this,"Sense.db",null,1);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv01 = (TextView) findViewById(R.id.tv_01);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv02 = (TextView) findViewById(R.id.tv_02);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv03 = (TextView) findViewById(R.id.tv_03);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv04 = (TextView) findViewById(R.id.tv_04);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv05 = (TextView) findViewById(R.id.tv_05);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv06 = (TextView) findViewById(R.id.tv_06);
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    /*sart();*/


    }public void c_tv2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public  void sart(){
        new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    httpThread = new HttpUtils();
                    httpThread2 = new HttpUtils();
                    String json2 = "{\"RoadId\":1,\"UserName\":\"user1\"}";
                    res2 =  httpThread2.sendPost("http://192.168.1.231:8080/TrafficServer/action/GetRoadStatus.do",json2);
                    String json = "{\"UserName\":" + "\"user1\"" + "}";
                    res1 =  httpThread.sendPost("http://192.168.1.231:8080/TrafficServer/action/GetAllSense.do",json);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();

    }

}
