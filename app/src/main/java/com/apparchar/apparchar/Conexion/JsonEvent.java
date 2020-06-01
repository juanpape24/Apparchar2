package com.apparchar.apparchar.Conexion;


import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.LugarM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonEvent {
    @GET("evento")
    Call<List<EventoM>> getEvento();

    @GET("lugar")
    Call<List<LugarM>> getLugar();

}
