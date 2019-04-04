package com.apparchar.apparchar.Conexion;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {

    private final Context context;
    OnLoopjCompleted loopjListener;
    AsyncHttpClient client;
    RequestParams params;
    //String MYURL = "http://192.168.137.1:8080/ServerApparchar/SERVRegister";
    String MYURL = "http://ec2-18-212-154-35.compute-1.amazonaws.com:8080/ServerApparchar-16501223344965549389.0-SNAPSHOT//";
    String jsonResponse;

    public MyLoopjTask(RequestParams parametros, String nameServlet, Context context, OnLoopjCompleted listener) {
        client = new AsyncHttpClient();
        params = parametros;
        MYURL = MYURL + nameServlet;
        this.context = context;
        this.loopjListener = listener;
    }

    public void executeLoopjCall() {

        client.post(MYURL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.taskCompleted(jsonResponse);
                Log.i("info", "onSuccess: " + jsonResponse);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("info", "onFailure: " + errorResponse);
            }
        });
    }


}
