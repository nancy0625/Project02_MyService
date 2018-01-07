package com.mad.trafficclient;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mad.trafficclient.httppost.HttpUtils;
import com.mad.trafficclient.httppost.JsonTools;

import java.util.ArrayList;
import java.util.List;

public class Project38Activity extends Activity implements View.OnClickListener {

    private ListView lv1;
    private MyAdapter adapter;
    private List<String> list,list3;
    private List<Car> list2;
    private Button btn1;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 001:
                    list = new ArrayList<String>();
                    list3 = new ArrayList<String>();
                    list2 = new ArrayList<Car>();
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("list");
                    String result2 = bundle.getString("list2");
                    list = new JsonTools().parseList2(result);
                    list3 = new JsonTools().parseList2(result2);

                    Car car = new Car();
                    Car car1 = new Car();
                    car.setCarId(1);
                    car.setStatus(Integer.valueOf(list.get(2)));
                    car1.setCarId(2);
                    car1.setStatus(Integer.valueOf(list3.get(2)));
                    list2.add(car);
                    list2.add(car1);


                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_38);

        lv1 = (ListView) findViewById(R.id.lv1);
        btn1 = (Button) findViewById(R.id.button) ;
        btn1.setOnClickListener(this);

       sta();
        lv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

    }
    private void sta(){
        new Thread(){
            @Override
            public void run() {
                String json = "{\"RoadId\":1,\"UserName\":\"user1\"}";
                String json2 = "{\"RoadId\":2,\"UserName\":\"user1\"}";
                String str1 = HttpUtils.send("http://192.168.191.1:8080/TrafficServer/action/GetRoadStatus.do",json);
                String str2 = HttpUtils.send("http://192.168.191.1:8080/TrafficServer/action/GetRoadStatus.do",json2);
                Message message = Message.obtain();
                message.what = 001;
                Bundle bundle = new Bundle();
                bundle.putString("list",str1);
                bundle.putString("list2",str2);
                message.setData(bundle);
                handler.sendMessage(message);


            }
        }.start();
    }

    private void btn_project(View view){
        sta();


      /*  adapter = new MyAdapter(this);*/
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                btn_project(v);
                adapter = new MyAdapter(list2,this);
                lv1.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
