package com.mad.trafficclient;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
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

public class FragmentProject11Fragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Spinner spinnerProject11;
    private ListView lvProject11;
    private MyAdapter adapter;
    private Thread mThread1;
    private SpinnerAdapter adapter1;
    private String res1;
    private String item;
    private List<Car> list = new ArrayList<Car>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle1 = msg.getData();
            String result = bundle1.getString("item");
            Log.i("dd",result);
            switch (msg.what){

                case 001:
                    if (result.equals("Money升序")){
                        sort1();
                    }else if (result.equals("Money降序")){
                        sort2();
                    }else if (result.equals("时间升序")){
                        sort1();
                    }else if (result.equals("时间降序")){
                        sort2();
                    }else if (result.equals("车号升序")){
                        sort1();
                    }else if (result.equals("车号降序")){
                        sort2();
                    }else {
                        sort1();
                    }
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
        spinnerProject11.setOnItemSelectedListener(this);



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
    private void sort2(){
        Collections.sort(list,new Comparator<Car>(){


            @Override
            public int compare(Car lhs, Car rhs) {
                if (lhs.getStauts()<rhs.getStauts()){
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
                    res1 = HttpUtils.send("http://192.168.191.1:8080/TrafficServer/action/GetRoadStatus.do",json);
                    parseJson();
                    Message message = Message.obtain();
                   Bundle bundle = new Bundle();
                    bundle.putString("item",item);
                    message.setData(bundle);
                    message.what = 001;
                    handler.sendMessage(message);

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
        if (list.size()>5){
            list.remove(0);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = (String) spinnerProject11.getItemAtPosition(position);
        setThread1();


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
