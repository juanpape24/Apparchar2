package com.apparchar.apparchar.IO;

import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Response.ResponseCliente;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("cliente/")
    Call<ResponseCliente> validacion(@Query("usuario") String usuario, @Query("contrasenia") String contrasenia);

    @POST("cliente/")
    Call<ClienteM> registro(@Body ClienteM cliente);


}