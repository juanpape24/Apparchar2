package com.apparchar.apparchar.Conexion;
import com.apparchar.apparchar.Modelo.CategoriaM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface JsonCategory{
    @GET("categoria")
    Call<List<CategoriaM>> getCategoria();
}
