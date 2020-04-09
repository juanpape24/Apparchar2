package com.apparchar.apparchar.IO;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

        private static ApiService API_SERVICE;

        public static ApiService getApiService() {

            // Creamos un interceptor y le indicamos el log level a usar
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Asociamos el interceptor a las peticiones
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            //String baseUrl = "https://api.themoviedb.org/3/movie/popular?api_key=09963e300150f9831c46a1828a82a984&language=en-US";
            String baseUrl = "http://192.168.137.138:8000/";

            if (API_SERVICE == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build()) // <-- usamos el log level
                        .build();
                API_SERVICE = retrofit.create(ApiService.class);
            }

            return API_SERVICE;
        }

}
