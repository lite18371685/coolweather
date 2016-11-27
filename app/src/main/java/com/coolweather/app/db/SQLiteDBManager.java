package com.coolweather.app.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by wales on 16/11/25.
 */
public class SQLiteDBManager {
    String filePath = "data/data/com.coolweather.app/cool_weather.db";

    String pathStr = "data/data/com.coolweather.app";

    public SQLiteDatabase openDatabase(Context context) {
        File jhPath = new File(filePath);
        if (jhPath.exists()) {
            //存在则直接打开数据库
            return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
        } else {
            //不存在先创建文件夹
            File path = new File(pathStr);
            if (path.mkdir()) {
                Log.d("Wales", "创建成果");
            } else {
                Log.d("Wales", "创建失败");
            }
            try {
                //得到资源
                AssetManager am = context.getAssets();
                InputStream is = am.open("cool_weather.db");
                FileOutputStream fos = new FileOutputStream(jhPath);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return openDatabase(context);
        }
    }
}
