package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.util.Log;

import com.apparchar.apparchar.Conexion.JsonApi;
import com.apparchar.apparchar.Conexion.JsonEvent;
import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Modelo.CategoriaM;
import com.apparchar.apparchar.Modelo.EventoCategoria;
import com.apparchar.apparchar.Modelo.EventoM;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaEventoPresenter implements ContractListaEvento.EventoPresenter {

    ContractListaEvento.ViewEvento vista;

    List<EventoM> lista = new ArrayList<>();
    List<CategoriaM> categoriaM = new ArrayList<>();
    JsonApi jsonEventApi;
    JsonApi jsonApi;
    Context context;

    public ListaEventoPresenter(ContractListaEvento.ViewEvento vista, Context context) {
        this.vista = vista;
        this.context = context;
        //jsonEventApi=new JsonEventApi();

        getPost();

    }

    private void getPost() {
        Call<List<EventoM>> call = jsonEventApi.getJsonEvent().getEvento();
        call.enqueue(new Callback<List<EventoM>>() {
            @Override
            public void onResponse(Call<List<EventoM>> call, Response<List<EventoM>> response) {
                if (!response.isSuccessful()) {
                    vista.showResult(String.valueOf(response.code()));
                    return;
                }
                List<EventoM> eventoMS = response.body();
                if (vista.getCat() != null) {
                    for (EventoM event1 : eventoMS) {
                        if (vista.getIdentificador().equals(String.valueOf(event1.getEvento().get(event1.getEvento().size() - 1).getCategoria()))) {
                            event1.setCategoria(vista.getCat());
                            lista.add(event1);
                        }
                    }

                } else if (vista.getCat() == null) {
                    for (EventoM event1 : eventoMS) {
                        lista.add(event1);
                    }
                }
                vista.dato(lista);
            }

            @Override
            public void onFailure(Call<List<EventoM>> call, Throwable t) {
                vista.showResult(t.getMessage());
            }
        });
    }

    private void getPostEvCa() {
        Call<List<CategoriaM>> call = jsonApi.getJsonCategory().getCategoria();
        Log.i("contract", "Lo que sea");
        call.enqueue(new Callback<List<CategoriaM>>() {
            @Override
            public void onResponse(Call<List<CategoriaM>> call, Response<List<CategoriaM>> response) {
                if (!response.isSuccessful()) {
                    Log.i("contract", "Lo que sea");
                    vista.showResult(String.valueOf(response.code()));
                    return;
                }
                Log.i("contract", "Lo que sea");
                List<CategoriaM> cate1 = response.body();
                for (CategoriaM cat : cate1) {
                    Log.i("contract", cate1.toString());
                    categoriaM.add(cat);
                }
            }


            @Override
            public void onFailure(Call<List<CategoriaM>> call, Throwable t) {
                Log.i("contract", "Lo que sea");
                vista.showResult(t.getMessage());
            }
        });
    }

}
