package com.mad.trafficclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.JsonTools;

import java.util.ArrayList;
import java.util.List;


public class Project35Activity extends Activity  {
    private TextView tv1 ;
    private List<String> list;
    private Notification notification;
    private NotificationManager notificationManager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
                case 001:
                  list = new ArrayList<String>();
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("list");
                    Log.i("ddd",result);
                    list = new JsonTools().parseList2(result);
                    if (Integer.valueOf(list.get(2))>3){
                        tv1.setText(list.get(2)+"");
                        notifiy("状态","status",list.get(2),"3",1);
                    }else {
                        tv1.setText(list.get(2)+"");
                        notificationManager.cancel(1);
                    }

                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_35);
        initView();
        sta();
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    private void initView(){
        tv1 = (TextView) findViewById(R.id.tv1);

    }
    private void sta(){
        new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    String json = "{\"RoadId\":1,\"UserName\":\"user1\"}";
                    String str1 = HttpUtils.send("http://192.168.1.183:8080/TrafficServer/action/GetRoadStatus.do",json);
                    Message message = Message.obtain();
                    message.what = 001;
                    Bundle bundle = new Bundle();
                    bundle.putString("list",str1);
                    message.setData(bundle);
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


    }
    private void notifiy(String title, String type, String nowValue, String maxValue,int num){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText("当前超标的数据类别:  " + type + "     当前值:" + nowValue + "   阈值:" + maxValue);
        builder.setDefaults(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setSmallIcon(android.R.drawable.ic_media_play);
        builder.setContentIntent(PendingIntent.getActivity(this, 0x12, new Intent(this,Project35Activity.class), 0));

        notification = builder.build();

        notificationManager.notify(num, notification);

    }


}
