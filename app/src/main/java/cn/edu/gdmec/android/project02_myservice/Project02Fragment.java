package cn.edu.gdmec.android.project02_myservice;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

public class Project02Fragment extends Fragment  {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.project02, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv01 = (TextView) view.findViewById(R.id.tv_01);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv02 = (TextView) view.findViewById(R.id.tv_02);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv03 = (TextView) view.findViewById(R.id.tv_03);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        tv04 = (TextView) view.findViewById(R.id.tv_04);
        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv05 = (TextView) view.findViewById(R.id.tv_05);
        tv6 = (TextView) view.findViewById(R.id.tv6);
        tv06 = (TextView) view.findViewById(R.id.tv_06);
    }

}
