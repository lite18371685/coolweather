package com.coolweather.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.app.R;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;


import com.coolweather.app.util.HttpCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.ParseJson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wales on 16/11/23.
 */
public class ChooseAreaActivity extends AppCompatActivity {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;

    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CoolWeatherDB coolWeatherDB;
    private List<String> dataList = new ArrayList<String>();
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县列表
     */
    private List<County> countyList;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 当前选中的级别
     */
    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        listView = (ListView) findViewById(R.id.list_view);
        titleText = (TextView) findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(index);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(index);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    County county = countyList.get(index);
                    Intent intent = new Intent(ChooseAreaActivity.this, Weather.class);
                    intent.putExtra("county_data", county);
                    startActivity(intent);
                }

            }

        });
//        queryFromServer();
//
//        showProgressDialog();
//        ParseJson.toCitySets(loadJson());
//        ParseJson.scaleCitySets(coolWeatherDB);
//        closeProgressDialog();

        queryProvinces();
    }

//    private String loadJson(){
//        StringBuilder sb=new StringBuilder();
//        try{
//            InputStream in=getAssets().open("china-city-list.json");
//            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//            String line;
//            while((line=reader.readLine())!=null){
//                sb.append(line);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }

    /**
     * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询。
     */

    private void queryProvinces() {
        provinceList = coolWeatherDB.loadProvince();
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {

                dataList.add(province.getProvinceZh());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        } else {

            Log.d("Wales", "cannot queryProvinces");

        }

    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCities() {
        cityList = coolWeatherDB.loadCity(selectedProvince.getProvinceZh());
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {

                dataList.add(city.getCityZh());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectedProvince.getProvinceZh());
            currentLevel = LEVEL_CITY;
        } else {
            Log.d("Wales", "cannot queryCities");
            //queryFromServer(selectedProvince.getProvinceZh(), "city");
        }

    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
     */

    private void queryCounties() {
        countyList = coolWeatherDB.loadCounties(selectedCity.getCityZh());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {

                dataList.add(county.getCountyZh());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectedCity.getCityZh());
            currentLevel = LEVEL_COUNTY;
        } else {
            Log.d("Wales", "cannot queryCounties");
            //queryFromServer(selectedCity.getCityZh(), "county");
        }

    }

    private void queryFromServer() {

        String address;
        address = "http://files.heweather.com/china-city-list.json";
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

                ParseJson.toCitySets(response);
                ParseJson.scaleCitySets(coolWeatherDB);
                closeProgressDialog();
            }

            @Override
            public void onError(Exception e) {
                Log.d("Wales", "HttpUtil Error!!");
                closeProgressDialog();
            }
        });

    }


    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载。。。");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else {
            finish();
        }
    }


}




