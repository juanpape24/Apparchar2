package com.apparchar.apparchar.IO;

import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Response.ResponseCliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("cliente")
    Call<ResponseCliente> getClientes();

    @POST("/posts")
    @FormUrlEncoded
    Call<ClienteM> registro(@Body ClienteM cliente);

    /*@POST("cliente")
    @FormUrlEncoded
    Call<ClienteM> registro(@Field("nombre") String nombre,
                            @Field("edad") int edad,
                            @Field("correo") String correo,
                            @Field("usuario") String usuario,
                            @Field("contrasenia") String contrasenia);*/


}