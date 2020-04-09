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

public interface ApiService {
    @GET("cliente")
    Call<ResponseCliente> getClientes();

    @POST("cliente/")
    Call<ClienteM> registro(@Body ClienteM cliente);


}