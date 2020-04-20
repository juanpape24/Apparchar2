package com.apparchar.apparchar.Conexion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonApi {

    private static JsonEvent API_SERVICE;
    private static JsonCategory API_SERVICE2;
    static String baseUrl = "http://192.168.0.7:8000/";
    public static JsonEvent getJsonEvent() {

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(JsonEvent.class);
        }

        return API_SERVICE;
    }
    public static JsonCategory getJsonCategory(){
        if (API_SERVICE2 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE2 = retrofit.create(JsonCategory.class);
        }

        return API_SERVICE2;
    }

}
