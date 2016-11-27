package com.coolweather.app.util;

import android.util.Log;

import com.coolweather.app.activity.Weather;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.CitySets;
import com.coolweather.app.model.County;
import com.coolweather.app.model.NowWeather;
import com.coolweather.app.model.Province;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wales on 16/11/23.
 */
public class ParseJson {


    public static void toNowWeather(String jsonData, NowWeather nowWeather) {


        try {

            JSONObject obj = new JSONObject(jsonData);
            JSONObject weather = obj.getJSONArray("HeWeather5").getJSONObject(0);
            JSONObject basic = weather.getJSONObject("basic");
            JSONObject now = weather.getJSONObject("now");
            float lat = (float) basic.getDouble("lat");
            float lon = (float) basic.getDouble("lon");
            String updateTime = basic.getJSONObject("update").getString("loc");
            int code = now.getJSONObject("cond").getInt("code");
            String txt = now.getJSONObject("cond").getString("txt");
            int fl = now.getInt("fl");
            int hum = now.getInt("hum");
            int pcpn = now.getInt("pcpn");
            int pres = now.getInt("pres");
            int tmp = now.getInt("tmp");
            int vis = now.getInt("vis");
            int windDeg = now.getJSONObject("wind").getInt("deg");
            String dir = now.getJSONObject("wind").getString("dir");
            String sc = now.getJSONObject("wind").getString("sc");
            int spd = now.getJSONObject("wind").getInt("spd");
            String status = weather.getString("status");
            //nowWeather=new NowWeather(lat,lon,updateTime,txt,code,fl,hum,pcpn,pres,tmp,vis,windDeg,dir,sc,spd,status);
            nowWeather.setLat(lat);
            nowWeather.setLon(lon);
            nowWeather.setUpdateTime(updateTime);
            nowWeather.setCode(code);
            nowWeather.setTxt(txt);
            nowWeather.setFl(fl);
            nowWeather.setHum(hum);
            nowWeather.setPcpn(pcpn);
            nowWeather.setPres(pres);
            nowWeather.setTmp(tmp);
            nowWeather.setVis(vis);
            nowWeather.setWindDeg(windDeg);
            nowWeather.setDir(dir);
            nowWeather.setSc(sc);
            nowWeather.setSpd(spd);
            nowWeather.setStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void toCitySets(String jsonData) {

        try {
            CitySets.clearAll();
            Log.d("Wales", "CitySets clear oK");
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String cityEn = jsonObject.getString("cityEn");
                String cityZh = jsonObject.getString("cityZh");
                String leaderEn = jsonObject.getString("leaderEn");
                String leaderZh = jsonObject.getString("leaderZh");
                String provinceEn = jsonObject.getString("provinceEn");
                String provinceZh = jsonObject.getString("provinceZh");
                float lat = (float) jsonObject.getDouble("lat");
                float lon = (float) jsonObject.getDouble("lon");
                CitySets.addToCitySets(new CitySets(id, cityEn, cityZh, leaderEn, leaderZh, provinceEn, provinceZh, lat, lon));
                Log.d("Wales", "CitySets add ok: " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scaleCitySets(CoolWeatherDB coolWeatherDB) {

        final List<CitySets> citySetsList = CitySets.getAllCities();

        List<City> cities = new ArrayList<City>();

        List<County> counties = new ArrayList<County>();

        List<Province> provinces = new ArrayList<Province>();


        for (CitySets sets : citySetsList) {

            Province province = new Province();
            province.setProvinceEn(sets.getProvinceEn());
            province.setProvinceZh(sets.getProvinceZh());
            provinces.add(province);
        }

        removeDuplicate(provinces);

        for (CitySets sets : citySetsList) {

            City city = new City();
            city.setCityEn(sets.getLeaderEn());
            city.setCityZh(sets.getLeaderZh());
            city.setProvinceName(sets.getProvinceZh());
            cities.add(city);
        }

        removeDuplicate(cities);

        for (CitySets sets : citySetsList) {

            County county = new County();
            county.setCountyEn(sets.getCityEn());
            county.setCountyZh(sets.getCityZh());
            county.setCountyCode(sets.getId());
            county.setCityName(sets.getLeaderZh());
            county.setLat(sets.getLat());
            county.setLon(sets.getLon());
            counties.add(county);
        }

        for (Province p : provinces) {
            coolWeatherDB.saveProvince(p);
        }

        for (City c : cities) {
            coolWeatherDB.saveCity(c);
        }

        for (County c : counties) {
            coolWeatherDB.saveCounty(c);
        }
    }

    public static void removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
    }
}
