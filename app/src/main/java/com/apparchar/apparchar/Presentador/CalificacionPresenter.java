package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.CalificacionPKM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.EventoPKM;
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
    CalificacionM calificacion;
    RequestParams params;
    ArrayList<String> comentarios;
    ArrayList<Double> porcentaje;
    ArrayList<byte[]> fotos;
    ArrayList<CalificacionM> calificaciones;
    String infoEvento = "";
    int opc = 0;
    CalificacionPKM calificacionPKM;
    EventoPKM eventoPKM;
    EventoM eventoM;


    public CalificacionPresenter(ContractCalificacion.ViewC vista) {
        this.vista = vista;
        calificacion = new CalificacionM();
        comentarios = new ArrayList<>();
        porcentaje = new ArrayList<>();
        fotos = new ArrayList<>();
        calificacionPKM = new CalificacionPKM();
        eventoM = new EventoM();
        eventoPKM = new EventoPKM();
    }


    @Override
    public void crearComentario(String comentario, String hora, String user, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion = new CalificacionM();
        calificacionPKM = new CalificacionPKM();
        eventoPKM = new EventoPKM();
        eventoM = new EventoM();
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacionPKM.setUsuariocliente(user);
        calificacionPKM.setIdevento(idEvento);
        eventoPKM.setHoraInicio(horaI);
        eventoPKM.setHoraFinal(horaF);
        eventoPKM.setFecha(fechaE);
        eventoPKM.setIdevento(idEvento);
        eventoM.setEventoPK(eventoPKM);
        calificacion.setEvento(eventoM);
        calificacion.setCalificacionPK(calificacionPKM);
        calificacion.setComentario(comentario);
        params = new RequestParams();
        Gson g = new Gson();
        String alv = g.toJson(calificacion);
        params.put("insertar", alv);
        String nameServlet = "SERVCalificacion";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc = 2;
    }

    @Override
    public void crearCalificacion(double porcentaje, String hora, String user, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion = new CalificacionM();
        calificacionPKM = new CalificacionPKM();
        eventoPKM = new EventoPKM();
        eventoM = new EventoM();
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacionPKM.setUsuariocliente(user);
        calificacionPKM.setIdevento(idEvento);
        eventoPKM.setHoraInicio(horaI);
        eventoPKM.setHoraFinal(horaF);
        eventoPKM.setFecha(fechaE);
        eventoPKM.setIdevento(idEvento);
        eventoM.setEventoPK(eventoPKM);
        calificacion.setEvento(eventoM);
        calificacion.setCalificacionPK(calificacionPKM);
        calificacion.setPorcentaje(porcentaje);
        params = new RequestParams();
        Gson g = new Gson();
        String alv = g.toJson(calificacion);
        params.put("insertar", alv);
        String nameServlet = "SERVCalificacion";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc = 2;
    }

    @Override
    public void crearMultimedia(byte[] multimedia, String hora, String user, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        calificacion = new CalificacionM();
        calificacionPKM = new CalificacionPKM();
        eventoPKM = new EventoPKM();
        eventoM = new EventoM();
        calificacion.setHora(hora);
        calificacion.setFecha(fecha);
        calificacionPKM.setUsuariocliente(user);
        calificacionPKM.setIdevento(idEvento);
        eventoPKM.setHoraInicio(horaI);
        eventoPKM.setHoraFinal(horaF);
        eventoPKM.setFecha(fechaE);
        eventoPKM.setIdevento(idEvento);
        eventoM.setEventoPK(eventoPKM);
        calificacion.setEvento(eventoM);
        calificacion.setCalificacionPK(calificacionPKM);
        calificacion.setMultimedia(multimedia);
        params = new RequestParams();
        Gson g = new Gson();
        String alv = g.toJson(calificacion);
        params.put("insertar", alv);
        String nameServlet = "SERVCalificacion";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc = 2;
    }

    @Override
    public String obtenerInfoEvento() {
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", vista.getIdEvento());
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc = 3;
        return infoEvento;
    }

    @Override
    public void actualizar(int idEvento) {
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", String.valueOf(idEvento));
        String nameServlet = "SERVCalificacion";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
        opc = 1;

        if (!porcentaje.isEmpty()) {
            vista.mostrarCalificacion(porcentaje);
            porcentaje = new ArrayList<>();
        }
        if (!comentarios.isEmpty()) {
            vista.mostrarComentarios(comentarios);
            comentarios = new ArrayList<>();
        }
        if (!fotos.isEmpty()) {
            vista.mostrarFotos(fotos);
            fotos = new ArrayList<>();
        }
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        if (opc == 1) {
            JsonElement lista = jo.get("respuesta");


            String listaR = lista.getAsString();
            Type listType = new TypeToken<ArrayList<CalificacionM>>() {
            }.getType();
            Gson a = new Gson();
            calificaciones = a.fromJson(listaR, listType);
            for (int i = 0; i < calificaciones.size(); i++) {
                if (calificaciones.get(i).getComentario() != null)
                    comentarios.add(calificaciones.get(i).getComentario());
                if (calificaciones.get(i).getPorcentaje() != null)
                    porcentaje.add(calificaciones.get(i).getPorcentaje());

                if (calificaciones.get(i).getMultimedia() != null)
                    fotos.add(calificaciones.get(i).getMultimedia());
            }


        } else if (opc == 2) {
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            if (r.equals("true")) {
                vista.showResult("Hecho");
            } else {
                vista.showResult("No se realizó correctamente");
            }
        } else if (opc == 3) {
            JsonElement c = jo.get("evento");
            infoEvento = c.getAsString();

        }

    }


}