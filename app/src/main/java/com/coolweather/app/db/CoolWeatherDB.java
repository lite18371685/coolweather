package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wales on 16/11/21.
 */
public class CoolWeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;


    /**
     * 将构造方法私有化
     */
    private CoolWeatherDB(Context context) {

        SQLiteDBManager manager = new SQLiteDBManager();
        db = manager.openDatabase(context);
    }

    /**
     * 获取CoolWeatherDB的实例
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 将Province实例存储到数据库
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_en", province.getProvinceEn());
            values.put("province_zh", province.getProvinceZh());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceEn(cursor.getString(cursor.getColumnIndex("province_en")));
                province.setProvinceZh(cursor.getString(cursor.getColumnIndex("province_zh")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_en", city.getCityEn());
            values.put("city_zh", city.getCityZh());
            values.put("province_name", city.getProvinceName());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库读取某生下所有的城市信息
     */
    public List<City> loadCity(String provinceName) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_name = ?", new String[]{provinceName}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityEn(cursor.getString(cursor.getColumnIndex("city_en")));
                city.setCityZh(cursor.getString(cursor.getColumnIndex("city_zh")));
                city.setProvinceName(provinceName);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例存储到数据库里面
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_en", county.getCountyEn());
            values.put("county_zh", county.getCountyZh());
            values.put("city_name", county.getCityName());
            values.put("county_code", county.getCountyCode());
            values.put("lat", county.getLat());
            values.put("lon", county.getLon());
            db.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取某城市下的下所有的县信息。
     */
    public List<County> loadCounties(String cityName) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_name = ?", new String[]{cityName}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyEn(cursor.getString(cursor.getColumnIndex("county_en")));
                county.setCountyZh(cursor.getString(cursor.getColumnIndex("county_zh")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setLat(cursor.getFloat(cursor.getColumnIndex("lat")));
                county.setLon(cursor.getFloat(cursor.getColumnIndex("lon")));

                county.setCityName(cityName);
                list.add(county);
            } while (cursor.moveToNext());

        }
        return list;

    }

}















