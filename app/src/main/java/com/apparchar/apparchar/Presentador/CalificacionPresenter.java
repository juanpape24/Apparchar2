package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.util.EventLog;
import android.util.Log;

import com.apparchar.apparchar.Conexion.JsonApi;
import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.EventoPKM;
import com.apparchar.apparchar.Vista.Cliente;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cz.msebera.android.httpclient.HttpResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificacionPresenter implements ContractCalificacion.PresenterC {
    ContractCalificacion.ViewC vista;
    CalificacionM calificacion;
    RequestParams params;
    ArrayList<Double> porcentaje;
    ArrayList<String> fotos;
    ArrayList<CalificacionM> comentarios;
    String infoEvento = "";
    EventoM eventoM;
    int opc = 0;
    ArrayList<ClienteM> clientes;


    public CalificacionPresenter(ContractCalificacion.ViewC vista) {
        this.vista = vista;
        calificacion = new CalificacionM();
        comentarios = new ArrayList<>();
        porcentaje = new ArrayList<>();
        fotos = new ArrayList<>();
        eventoM = new EventoM();
        clientes = new ArrayList<>();


    }


    @Override
    public void crearComentario(String comentario, String hora, String user, String fecha) {
        calificacion = new CalificacionM();
        calificacion.setComentario(comentario);
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setEvento(eventoM.getId());
        calificacion.setUsuariocliente(user);
        JsonApi.getApiService().doCalification(calificacion).enqueue(new Callback<CalificacionM>() {
            @Override
            public void onResponse(Call<CalificacionM> call, Response<CalificacionM> response) {
                vista.showResult("Hecho");
            }

            @Override
            public void onFailure(Call<CalificacionM> call, Throwable t) {
                //vista.showResult("No se realizó correctamente");
                vista.showResult(t.toString());
            }
        });

    }

    @Override
    public void crearCalificacion(double porcentaje, String hora, String user, String fecha) {
        calificacion = new CalificacionM();
        calificacion.setPorcentaje(porcentaje);
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setEvento(eventoM.getId());
        calificacion.setUsuariocliente(user);
        JsonApi.getApiService().doCalification(calificacion).enqueue(new Callback<CalificacionM>() {
            @Override
            public void onResponse(Call<CalificacionM> call, Response<CalificacionM> response) {
                vista.showResult("Hecho");
            }

            @Override
            public void onFailure(Call<CalificacionM> call, Throwable t) {
                vista.showResult("No se realizó correctamente");
            }
        });
    }

    @Override
    public void crearMultimedia(File multimedia, String hora, String user, String fecha) {
        calificacion = new CalificacionM();
        Long tslong = System.currentTimeMillis() / 1000;
        String ts = tslong.toString();
        Random r = new Random();
        String n1 = String.valueOf(r.nextInt(10));
        String n2 = String.valueOf(r.nextInt(10));
        String ruta = "FotosCalificacion/" + eventoM.getId() + "/" + tslong + n1 + n2;
        calificacion.setMultimedia(ruta);
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setEvento(eventoM.getId());
        calificacion.setUsuariocliente(user);

        JsonApi.getApiService().doCalification(calificacion).enqueue(new Callback<CalificacionM>() {
            @Override
            public void onResponse(Call<CalificacionM> call, Response<CalificacionM> response) {
                vista.showResult("Hecho");
            }

            @Override
            public void onFailure(Call<CalificacionM> call, Throwable t) {
                vista.showResult("No se realizo\u00f3 correctamente");
            }
        });

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), multimedia);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto", user, requestBody);

        JsonApi.getApiService().uploadFoto(body, ruta).enqueue(new Callback<ClienteM>() {
            @Override
            public void onResponse(Call<ClienteM> call, Response<ClienteM> response) {

            }

            @Override
            public void onFailure(Call<ClienteM> call, Throwable t) {
                vista.showResult("No se pudo subir la foto");
            }
        });
    }


    @Override
    public void actualizar(String idEvento) {
        getEvento(idEvento);
        JsonApi.getApiService().getCalificaciones(Integer.parseInt(idEvento)).enqueue(new Callback<List<CalificacionM>>() {
            @Override
            public void onResponse(Call<List<CalificacionM>> call, Response<List<CalificacionM>> response) {

                ArrayList<CalificacionM> calificacionMS = (ArrayList<CalificacionM>) response.body();
                Boolean emptyCom = true;
                Boolean emptyFotos = true;
                for (int i = 0; i < calificacionMS.size(); i++) {
                    if (calificacionMS.get(i).getComentario() != null) {
                        comentarios.add(calificacionMS.get(i));
                        emptyCom = false;
                    } else {

                    }

                    if (calificacionMS.get(i).getPorcentaje() != null)
                        porcentaje.add(calificacionMS.get(i).getPorcentaje());

                    if (calificacionMS.get(i).getMultimedia() != null) {
                        fotos.add(calificacionMS.get(i).getMultimedia());
                        emptyFotos = false;
                    } else {

                    }

                }

                if (emptyCom) {
                    vista.showResult("Au\u00f3n no hay comentarios");
                }
                if (emptyFotos) {
                    vista.showResult("Au\u00f3n no hay Fotos");
                }
                getClientes();


            }

            @Override
            public void onFailure(Call<List<CalificacionM>> call, Throwable t) {
                vista.showResult(t.toString());
            }
        });


    }



    @Override
    public void update() {

        if (!porcentaje.isEmpty()) {
            vista.mostrarCalificacion(porcentaje);
            porcentaje = new ArrayList<>();
        }
        if (!comentarios.isEmpty()) {
            ClienteM clienteM = new ClienteM();

            vista.mostrarComentarios(comentarios, clientes);
            comentarios = new ArrayList<>();
        }
        if (!fotos.isEmpty()) {
            vista.mostrarFotos(fotos);
            fotos = new ArrayList<>();
        }
        //vista.mostrarEvento(eventoM);


    }

    @Override
    public void getEvento(String idEvento) {
        JsonApi.getApiService().getOnlyEvent(Integer.parseInt(idEvento)).enqueue(new Callback<EventoM>() {

            @Override
            public void onResponse(Call<EventoM> call, Response<EventoM> response) {
                eventoM = response.body();

                vista.mostrarEvento(response.body());
            }

            @Override
            public void onFailure(Call<EventoM> call, Throwable t) {
                Log.e("errorEvento", t.toString());

            }
        });


    }


    private void getClientes() {
        JsonApi.getApiService().getClientes().enqueue(new Callback<List<ClienteM>>() {
            @Override
            public void onResponse(Call<List<ClienteM>> call, Response<List<ClienteM>> response) {
                ArrayList<ClienteM> lista = (ArrayList<ClienteM>) response.body();
                clientes = new ArrayList<>();
                clientes.addAll(lista);
                update();

            }

            @Override
            public void onFailure(Call<List<ClienteM>> call, Throwable t) {

                vista.showResult(t.toString());

            }
        });

    }


}


