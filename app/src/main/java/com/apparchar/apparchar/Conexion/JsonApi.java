package com.apparchar.apparchar.Conexion;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonApi {

    private static JsonEvent API_SERVICE;
    private static JsonCategory API_SERVICE2;
    private static ApiService API_SERVICE3;
    static String baseUrl = "http://192.168.0.14:8000/";
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
    public static ApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        if (API_SERVICE3 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE3 = retrofit.create(ApiService.class);
        }

        return API_SERVICE3;
    }

}
