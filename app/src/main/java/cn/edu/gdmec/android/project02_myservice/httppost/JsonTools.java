package cn.edu.gdmec.android.project02_myservice.httppost;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asus on 2018/1/4.
 */

public class JsonTools {

    public static List<String> parseList1(String json){
        List<String> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = (String) jsonObject.get(key);
                list.add(value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("list",list.toString());
        return list;
    }
}
