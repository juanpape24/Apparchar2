package com.apparchar.apparchar.IO;

import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Vista.Cliente;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @GET("cliente/")
    Call<ClienteM> validacion(@Query("usuario") String usuario, @Query("contrasenia") String contrasenia);

    @POST("cliente/")
    Call<ClienteM> registro(@Body ClienteM cliente);

    @Multipart
    @POST("cliente/uploadFoto")
    Call<ClienteM> uploadFile(@Part MultipartBody.Part file);



}