package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wales on 16/11/23.
 */
public class CitySets {

    //id:城市id  cityEn:城市英文  cityZh:城市中文  countryCode:国家代码
    // countryEn:国家英文  countryZh:国家中文  provinceEn:省英文  provinceEn:省中文
    // provinceEn:省中文  leaderEn:所属上级市英文  leaderZh:所属上级市中文
    // lat:纬度  lon:经度
    //ex:{"id":"CN101010100","cityEn":"beijing","cityZh":"北京","countryCode":"CN","countryEn":"China","countryZh":"中国","provinceEn":"beijing","provinceZh":"北京","leaderEn":"beijing","leaderZh":"北京","lat":"39.904","lon":"116.391"}


    String id, cityEn, cityZh, leaderEn, leaderZh, provinceEn, provinceZh;
    float lat, lon;

    private static List<CitySets> allCities = new ArrayList<CitySets>();

    public static List<CitySets> getAllCities() {
        return allCities;
    }

    public static void addToCitySets(CitySets c) {
        allCities.add(c);
    }

    public static void clearAll() {
        allCities.clear();
    }


    public CitySets(String id, String cityEn, String cityZh, String leaderEn, String leaderZh, String provinceEn, String provinceZh, float lat, float lon) {
        this.id = id;
        this.cityEn = cityEn;
        this.cityZh = cityZh;
        this.leaderEn = leaderEn;
        this.leaderZh = leaderZh;
        this.provinceEn = provinceEn;
        this.provinceZh = provinceZh;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getLeaderEn() {
        return leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }

    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
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
}
