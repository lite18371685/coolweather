package com.coolweather.app.model;

/**
 * Created by wales on 16/11/26.
 */
public class NowWeather {

    float lat, lon;
    String updateTime, txt;
    int code;
    int fl;//体感温度
    int hum;//相对湿度
    int pcpn;//降水量mm
    int pres;//气压
    int tmp;//温度
    int vis;//能见度 Km
    int windDeg;//
    String dir;//风向
    String sc;//风力
    int spd;//风速 kmphf
    String status;//接口状态；


    public NowWeather(float lat, float lon, String updateTime, String txt, int code, int fl, int hum, int pcpn, int pres, int tmp, int vis, int windDeg, String dir, String sc, int spd, String status) {
        this.lat = lat;
        this.lon = lon;
        this.updateTime = updateTime;
        this.txt = txt;
        this.code = code;
        this.fl = fl;
        this.hum = hum;
        this.pcpn = pcpn;
        this.pres = pres;
        this.tmp = tmp;
        this.vis = vis;
        this.windDeg = windDeg;
        this.dir = dir;
        this.sc = sc;
        this.spd = spd;
        this.status = status;
    }

    public NowWeather() {

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTxt() {
        return txt;
    }

    @Override
    public String toString() {
        return "NowWeather{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", updateTime='" + updateTime + '\'' +
                ", txt='" + txt + '\'' +
                ", code=" + code +
                ", fl=" + fl +
                ", hum=" + hum +
                ", pcpn=" + pcpn +
                ", pres=" + pres +
                ", tmp=" + tmp +
                ", vis=" + vis +
                ", windDeg=" + windDeg +
                ", dir='" + dir + '\'' +
                ", sc='" + sc + '\'' +
                ", spd=" + spd +
                ", status='" + status + '\'' +
                '}';
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getPcpn() {
        return pcpn;
    }

    public void setPcpn(int pcpn) {
        this.pcpn = pcpn;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }

    public int getVis() {
        return vis;
    }

    public void setVis(int vis) {
        this.vis = vis;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
