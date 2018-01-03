package cn.edu.gdmec.android.project02_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import cn.edu.gdmec.android.project02_myservice.httppost.HttpUtils;

/**
 * Created by asus on 2018/1/3.
 */

public class MyService extends Service {
    private HttpUtils httpThread,httpThread2;
    public MyService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    httpThread = new HttpUtils();
                    httpThread2 = new HttpUtils();
                    String json2 = "{\"RoadId\":1,\"UserName\":\"user1\"}";
                    httpThread2.sendPost("http://192.168.1.231:8080/TrafficServer/action/GetRoadStatus.do",json2);
                    String json = "{\"UserName\":" + "\"user1\"" + "}";
                    httpThread.sendPost("http://192.168.1.231:8080/TrafficServer/action/GetAllSense.do",json);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
