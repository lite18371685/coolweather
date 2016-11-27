package com.coolweather.app.util;

/**
 * Created by wales on 16/11/23.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
