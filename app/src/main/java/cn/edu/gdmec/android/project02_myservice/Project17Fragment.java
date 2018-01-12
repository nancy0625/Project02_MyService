package com.mad.trafficclient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.mad.trafficclient.httppost.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Project17Fragment extends Fragment implements View.OnClickListener {

    private TextView tv1P17;
    private TextView textView2;
    private TextView t1P17;
    private TextView t2P17;
    private String str1;
    private String str2;
    private List<Integer> list;
    private List<Integer> list1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 001:
                    if (getActivity() != null) {
                        Toast toast = Toast.makeText(getActivity(), "当前网络状态码：" + HttpUtils.status, Toast.LENGTH_SHORT);
                        showMyToast(toast,500);
                        up();
                        up2();
                        Log.i("list1", list.toString());
                    }
                    break;
                case 002:
                    Toast.makeText(getActivity(), "当前网络状态码：" + HttpUtils.status, Toast.LENGTH_SHORT);
                    break;
                case 003:
                    if (getActivity() != null) {
                    Toast toast =    Toast.makeText(getActivity(), "当前网络状态码：" + HttpUtils.status, Toast.LENGTH_SHORT);
                        showMyToast(toast,500);
                        up1();
                        up2();
                        Log.i("list2", list1.toString());
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.project17, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1P17 = (TextView) view.findViewById(R.id.tv1_p17);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        view.findViewById(R.id.btn_p17).setOnClickListener(this);
        t1P17 = (TextView) view.findViewById(R.id.t1_p17);
        t2P17 = (TextView) view.findViewById(R.id.t2_p17);
    }

    private void jsonPase() {
        list = new ArrayList<Integer>();
        try {
            JSONObject jsonObject = new JSONObject(str1);
            list.add(jsonObject.getInt("Status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void jsonPase1() {
        list1 = new ArrayList<Integer>();
        try {
            JSONObject jsonObject = new JSONObject(str2);
            list1.add(jsonObject.getInt("Status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void st() {
        new Thread() {
            @Override
            public void run() {

                String json = "{\"RoadId\":" + 1 + ",\"UserName\":\"user1\"}";
                str1 = HttpUtils.send("http://192.168.1.183:8080/TrafficServer/action/GetRoadStatus.do", json);
                if (HttpUtils.status == 200) {
                    jsonPase();

                    handler.sendEmptyMessage(001);
                } else {
                    handler.sendEmptyMessage(002);
                }
            }
        }.start();
    }

    private void sta() {
        new Thread() {
            @Override
            public void run() {

                String json = "{\"RoadId\":" + 2 + ",\"UserName\":\"user1\"}";
                str2 = HttpUtils.send("http://192.168.1.183:8080/TrafficServer/action/GetRoadStatus.do", json);
                if (HttpUtils.status == 200) {
                    jsonPase1();
                    handler.sendEmptyMessage(003);
                } else {
                    handler.sendEmptyMessage(002);
                }
            }
        }.start();

    }

    private void up() {
        if (list.get(0) > 3) {
            t1P17.setBackgroundResource(R.drawable.cartwo);
        }
        if (list.get(0) <= 3) {
            t1P17.setBackgroundResource(R.drawable.carone);
            final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            dialog.setTitle("提示");
            dialog.setMessage("一号车位当前空闲   空闲时间：" + 30 + "分钟");
            dialog.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            }.start();
        }
    }

    private void up1() {
        if (list1.get(0) > 3) {
            t2P17.setBackgroundResource(R.drawable.cartwo);
        }
        if (list1.get(0) <= 3) {
            t2P17.setBackgroundResource(R.drawable.carone);
            final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            dialog.setTitle("提示");
            dialog.setMessage("二号车位当前空闲   空闲时间：" + 30 + "分钟");
            dialog.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            }.start();
        }
    }
    private void showMyToast(final Toast toast,final int cnt){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        },0,3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        },cnt);
    }

    private void up2() {
        if (list.get(0) <= 3 && list1.get(0) <= 3) {
            t1P17.setBackgroundResource(R.drawable.carone);
            t2P17.setBackgroundResource(R.drawable.carone);
            final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

            dialog.setTitle("提示");
            dialog.setMessage("一号、二号车位当前空闲   空闲时间：" + 30 + "分钟");
            dialog.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            }.start();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_p17:
                st();
                sta();
                break;
        }
    }
}
