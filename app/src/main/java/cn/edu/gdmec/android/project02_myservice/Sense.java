package cn.edu.gdmec.android.project02_myservice;

/**
 * Created by asus on 2018/1/3.
 */

public class Sense {
    public int tem;
    public int hum;
    public int light;
    public int pm2;
    public int co2;
    public String timer;
    public int status;

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getPm2() {
        return pm2;
    }

    public void setPm2(int pm2) {
        this.pm2 = pm2;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
