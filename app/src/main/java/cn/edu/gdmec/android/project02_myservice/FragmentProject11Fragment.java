package com.mad.trafficclient;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import com.mad.trafficclient.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentProject11Fragment extends Fragment {

    private Spinner spinnerProject11;
    private ListView lvProject11;
    private MyAdapter adapter;
    private Thread mThread1;
    private SpinnerAdapter adapter1;
    private String res1;
    private List<Car> list = new ArrayList<Car>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 001:

                    sort1();
                    adapter.notifyDataSetChanged();
                    break;
            }


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project11, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerProject11 = (Spinner) view.findViewById(R.id.spinner_project11);
        lvProject11 = (ListView) view.findViewById(R.id.lv_project11);
        adapter= new MyAdapter(list,getActivity());
        lvProject11.setAdapter(adapter);
        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getData());
        spinnerProject11.setAdapter( adapter1);
        setThread1();

    }
    private List<String> getData(){
        List<String> list = new ArrayList<String>();
        list.add("Money升序");
        list.add("车号升序");
        list.add("时间降序");
        list.add("时间升序");
        list.add("Money降序");
        list.add("车号降序");

        return list;
    }
    private void sort1(){
        Collections.sort(list,new Comparator<Car>(){


            @Override
            public int compare(Car lhs, Car rhs) {
                if (lhs.getStauts()>rhs.getStauts()){
                    return 1;
                }
                return -1;
            }
        });
    }
    private void setThread1(){
        mThread1 = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String json = "{\"RoadId\":1,\"UserName\":\"user1\"}";
                    res1 = HttpUtils.send("http://192.168.1.183:8080/TrafficServer/action/GetRoadStatus.do",json);
                    parseJson();
                    handler.sendEmptyMessage(001);

                }
            }
        };
        mThread1.start();
    }

    private void parseJson(){
        try {
            JSONObject jsonObject = new JSONObject(res1);
           Car car = new Car();
            car.setCarId(jsonObject.getString("RESULT"));
            car.setNowDate(jsonObject.getString("ERRMSG"));
            car.setStauts(jsonObject.getInt("Status"));
            list.add(car);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
