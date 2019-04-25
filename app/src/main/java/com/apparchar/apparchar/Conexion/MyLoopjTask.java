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

   // String MYURL = "http://192.168.137.1:8080/ServerApparchar/"; //pc jefri
    //String MYURL = "http://ec2-54-173-136-67.compute-1.amazonaws.com:8080/ServerApparchar-19201017617878744913.0-SNAPSHOT/"; //glasfish 5
   String MYURL="http://ec2-54-146-23-68.compute-1.amazonaws.com:8080/ServerApparchar-1.0-SNAPSHOT/"; //glasfish4

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
