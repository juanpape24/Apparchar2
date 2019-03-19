package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Modelo.Calificacion;
import com.apparchar.apparchar.VO.CategoriaVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalificacionPresenter implements ContractCalificacion.PresenterC, OnLoopjCompleted {
    ContractCalificacion.ViewC vista;
    Calificacion calificacion;
    RequestParams params;
    ArrayList<String> comentarios;
    ArrayList<Float> porcentaje;
    ArrayList<byte[]> fotos;
    String infoEvento="";
    int opc=0;

    public CalificacionPresenter(ContractCalificacion.ViewC vista) {
        this.vista = vista;
        calificacion= new Calificacion();
        comentarios=new ArrayList<>();
        porcentaje=new ArrayList<>();
        fotos=new ArrayList<>();
    }




    @Override
    public void crearComentario(String comentario, String hora, int idUser, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setHoraI(horaI);
        calificacion.setHoraF(horaF);
        calificacion.setFechaE(fechaE);
        calificacion.setIdUser(idUser);
        calificacion.setIdEvento(idEvento);
        calificacion.setComentario(comentario);
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc=2;
    }

    @Override
    public void crearCalificacion(float porcentaje, String hora, int idUser, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setHoraI(horaI);
        calificacion.setHoraF(horaF);
        calificacion.setFechaE(fechaE);
        calificacion.setIdUser(idUser);
        calificacion.setIdEvento(idEvento);
        calificacion.setPorcentaje(porcentaje);
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc=2;
    }

    @Override
    public void crearMultimedia(byte[] multimedia, String hora, int idUser, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacion.setHoraI(horaI);
        calificacion.setHoraF(horaF);
        calificacion.setFechaE(fechaE);
        calificacion.setIdUser(idUser);
        calificacion.setIdEvento(idEvento);
        calificacion.setMultimedia(multimedia);
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc=2;
    }

    @Override
    public String obtenerInfoEvento() {
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", vista.getIdEvento());
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc=3;
        return infoEvento;
    }

    @Override
    public void actualizar() {
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc=1;


    vista.mostrarCalificacion(porcentaje);
    vista.mostrarComentarios(comentarios);
    vista.mostrarFotos(fotos);
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        if (opc==1){
            JsonElement com = jo.get("respuesta");
            JsonElement mlt = jo.get("respuesta");
            JsonElement pcr = jo.get("respuesta");

            String comR = com.getAsString();
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            Gson a = new Gson();
            comentarios = a.fromJson(comR, listType);

            String pcrR = pcr.getAsString();
            Type listType2 = new TypeToken<ArrayList<Float>>() {
            }.getType();
            Gson a2 = new Gson();
            porcentaje = a2.fromJson(pcrR, listType2);

            String mltR = mlt.getAsString();
            Type listType3 = new TypeToken<ArrayList<byte[]>>() {
            }.getType();
            Gson a3 = new Gson();
            fotos = a3.fromJson(mltR, listType3);
        } else if(opc==2){
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            if (r.equals("true")) {
                vista.showResult("Hecho");
            } else {
                vista.showResult("No se realizó correctamente");
            }
        } else if(opc==3){
            JsonElement c = jo.get("evento");
            infoEvento = c.getAsString();

        }

    }




}