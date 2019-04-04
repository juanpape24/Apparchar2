package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractListaEvento;
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

public class ListaEventoPresenter implements ContractListaEvento.EventoPresenter, OnLoopjCompleted {

    ContractListaEvento.ViewEvento vista;

    List<EventoM> lista = new ArrayList<>();
    RequestParams params;

    public ListaEventoPresenter(ContractListaEvento.ViewEvento vista) {
        this.vista = vista;
        params = new RequestParams();
        params.put("listar", "gg");
        String nameServlet = "SERVEvento";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
    }


    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        Type listType = new TypeToken<ArrayList<EventoM>>() {
        }.getType();
        Gson a = new Gson();
        ArrayList<EventoM> arrayList = a.fromJson(r, listType);
        for (int i = 0; i < arrayList.size(); i++) {
            lista.add(arrayList.get(i));
        }
        vista.dato(lista);
    }
}
