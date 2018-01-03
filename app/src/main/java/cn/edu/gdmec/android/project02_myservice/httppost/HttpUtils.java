package cn.edu.gdmec.android.project02_myservice.httppost;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;

/**
 * Created by asus on 2018/1/3.
 */

public class HttpUtils {
    public String sendPost(String url,String json){
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type","application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(json.getBytes("utf-8"));
            outputStream.flush();
            InputStream is = httpURLConnection.getInputStream();
            if (httpURLConnection.getResponseCode() == 200){
                byte [] data = new byte[1024];
                int len = 0;
                while ((len = is.read()) != -1){
                    bao.write(data,0,len);
                }
                is.close();
            }
            else {
                Log.i("--result","连接失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(bao.toByteArray());
    }
}
