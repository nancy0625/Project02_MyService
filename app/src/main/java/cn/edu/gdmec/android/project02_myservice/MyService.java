package cn.edu.gdmec.android.project02_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import cn.edu.gdmec.android.project02_myservice.httppost.HttpUtils;

/**
 * Created by asus on 2018/1/3.
 */

public class MyService extends Service {
    private HttpUtils httpThread,httpThread2;
    private String res1,res2;
    public MyService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       new Project02Activity().sart();

        return super.onStartCommand(intent, flags, startId);
    }
}
