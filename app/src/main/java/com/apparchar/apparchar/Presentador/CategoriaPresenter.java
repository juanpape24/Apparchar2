package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractListaCategoria;
import com.apparchar.apparchar.Modelo.CategoriaM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoriaPresenter implements ContractListaCategoria.presenterCategoria, OnLoopjCompleted {

    ContractListaCategoria.viewCategoria vista;
    ArrayList<String> categoria=new ArrayList<>();
    RequestParams params;
    public CategoriaPresenter(ContractListaCategoria.viewCategoria vista){
        this.vista=vista;
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        Type listType = new TypeToken<ArrayList<CategoriaM>>() {
        }.getType();
        Gson a = new Gson();
        ArrayList<CategoriaM> arrayList = a.fromJson(r, listType);
        for (int i = 0; i < arrayList.size(); i++) {
            categoria.add(arrayList.get(i).getNombre());
        }
        vista.datos(categoria);
    }
}
