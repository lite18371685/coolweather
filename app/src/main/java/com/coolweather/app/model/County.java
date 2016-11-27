package com.coolweather.app.model;

import java.io.Serializable;

/**
 * Created by wales on 16/11/21.
 */
public class County implements Serializable {
    private int id;
    private String countyEn, countyZh, cityName, countyCode;
    private float lat, lon;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyEn() {
        return countyEn;
    }

    public void setCountyEn(String countyEn) {
        this.countyEn = countyEn;
    }

    public String getCountyZh() {
        return countyZh;
    }

    public void setCountyZh(String countyZh) {
        this.countyZh = countyZh;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
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
