package com.coolweather.app.activity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolweather.app.R;
import com.coolweather.app.model.County;
import com.coolweather.app.model.NowWeather;
import com.coolweather.app.util.HeWeatherAPI;
import com.coolweather.app.util.HttpCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.HttpUtil2;
import com.coolweather.app.util.ParseJson;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by wales on 16/11/26.
 */
public class Weather extends AppCompatActivity {


    County county;

    NowWeather nowWeather;

    private Button chooseCity;

    private ImageView imgView;

    private TextView areaText, timeText, tempText, txtText, latlonText, flText, humText, pcpnText, presText, visText, dirText, scText, spdText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        nowWeather = new NowWeather();
        chooseCity = (Button) findViewById(R.id.choose_city);
        imgView = (ImageView) findViewById(R.id.img);
        areaText = (TextView) findViewById(R.id.area_text);
        timeText = (TextView) findViewById(R.id.time_text);
        tempText = (TextView) findViewById(R.id.temp);
        txtText = (TextView) findViewById(R.id.weather_txt);
        latlonText = (TextView) findViewById(R.id.lat_lon_txt);
        flText = (TextView) findViewById(R.id.fl_text);
        humText = (TextView) findViewById(R.id.hum_text);
        pcpnText = (TextView) findViewById(R.id.pcpn_text);
        presText = (TextView) findViewById(R.id.pres_text);
        visText = (TextView) findViewById(R.id.vis_text);
        dirText = (TextView) findViewById(R.id.dir_text);
        scText = (TextView) findViewById(R.id.sc_text);
        spdText = (TextView) findViewById(R.id.spd_text);

        county = (County) getIntent().getSerializableExtra("county_data");
        String json = getNowWeatherJSON(county.getCountyCode());
        Log.d("wales", json);
        ParseJson.toNowWeather(json, nowWeather);


        imgView.setImageResource(R.drawable.p100);
//        if(nowWeather==null){
//            Log.d("wales","noWeather is null");
//            AlertDialog.Builder dialog=new AlertDialog.Builder(Weather.this);
//            dialog.setTitle("This is Dialog"); dialog.setMessage("Something important.");
//            dialog.show();
//
//        }
        imgView.setImageResource(getImageResourceId("p" + nowWeather.getCode()));
        Log.d("wales", nowWeather.toString());
        areaText.setText(county.getCountyZh());
        timeText.setText("更新时间：" + nowWeather.getUpdateTime());
        tempText.setText("" + nowWeather.getTmp() + "℃");
        txtText.setText(nowWeather.getTxt());
        String str = "纬度：" + nowWeather.getLat() + "  经度：" + nowWeather.getLon();
        latlonText.setText(str);
        flText.setText("" + nowWeather.getFl() + "℃");
        humText.setText("" + nowWeather.getHum() + "%");
        pcpnText.setText(nowWeather.getPcpn() + "mm");
        presText.setText(nowWeather.getPres() + "mPa");
        visText.setText(nowWeather.getVis() + "km");
        dirText.setText(nowWeather.getDir());
        scText.setText(nowWeather.getSc() + "m/s");
        spdText.setText(nowWeather.getSpd() + "级");

        Log.d("wales", txtText.getText().toString());


    }

    private String getNowWeatherJSON(String code) {
        String str = HeWeatherAPI.NOWURL + "&city=" + code;

        String result;

        result = HttpUtil2.sendHttpRequest(str);

        return result;

    }


    public int getImageResourceId(String name) {
        R.drawable drawables = new R.drawable();
        //默认的id
        int resId = 0x7f02000b;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field = R.drawable.class.getField(name);
            //取值
            resId = (Integer) field.get(drawables);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resId;
    }

}
