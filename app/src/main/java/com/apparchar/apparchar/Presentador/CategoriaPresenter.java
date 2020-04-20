package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.JsonApi;
import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractListaCategoria;
import com.apparchar.apparchar.Modelo.CategoriaM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaPresenter implements ContractListaCategoria.presenterCategoria{

    ContractListaCategoria.viewCategoria vista;
    ArrayList<CategoriaM> categoria=new ArrayList<>();
    JsonApi jsonApi;
    Context context;
    public CategoriaPresenter(ContractListaCategoria.viewCategoria vista, Context context){
        this.vista=vista;
        this.context=context;
        getPost();
    }
    private void getPost(){
        Call<List<CategoriaM>> call= jsonApi.getJsonCategory().getCategoria();
        call.enqueue(new Callback<List<CategoriaM>>() {
            @Override
            public void onResponse(Call<List<CategoriaM>> call, Response<List<CategoriaM>> response) {
                if(!response.isSuccessful()){
                    vista.showResult(String.valueOf(response.code()));
                    return;
                }

                List<CategoriaM> categoriaMS=response.body();
                for (CategoriaM categoria1: categoriaMS) {
                    categoria.add(categoria1);
                }
                vista.datos(categoria);
            }

            @Override
            public void onFailure(Call<List<CategoriaM>> call, Throwable t) {
                vista.showResult(t.getMessage());
            }
        });
    }
}
