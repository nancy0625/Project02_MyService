package com.mad.trafficclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2018/1/6.
 */

public class MyAdapter extends BaseAdapter {
    private List<Car> list;
    private LayoutInflater mInflater;

    public MyAdapter(List<Car> list,Context context) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.project_38item,null);
            viewHolder.tv1 = (TextView)convertView.findViewById(R.id.tv_project_38);
            viewHolder.tv2 = (TextView)convertView.findViewById(R.id.tv_38);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Car car = list.get(position);
        if (car.getStatus()>4){
            viewHolder.tv1.setBackgroundResource(R.drawable.benchi);
            viewHolder.tv2.setText("第"+car.carId+"号道路红色饱和");

        }  else if (car.getStatus()>3){
            viewHolder.tv1.setBackgroundResource(R.drawable.baoma);
            viewHolder.tv2.setText("第"+car.carId+"号道路一般拥挤");
        }else  {
            viewHolder.tv1.setBackgroundResource(R.drawable.audi);
            viewHolder.tv2.setText("第"+car.carId+"号道路畅通");
        }


        return convertView;
    }
    class ViewHolder{
        public TextView tv1;
        public TextView tv2;
    }
}
