package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.CalificacionPKM;
import com.apparchar.apparchar.Modelo.ClienteM;
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
import java.util.Collection;

public class CalificacionPresenter implements ContractCalificacion.PresenterC, OnLoopjCompleted {
    ContractCalificacion.ViewC vista;
    CalificacionM calificacion;
    RequestParams params;
    ArrayList<Double> porcentaje;
    ArrayList<byte[]> fotos;
    ArrayList<CalificacionM> comentarios;
    String infoEvento = "";
    int opc = 0;
    EventoPKM eventoPKM;
    EventoM eventoM;
    ClienteM cliente;
    CalificacionPKM calificacionPKM;
    ArrayList<EventoM> eventos;


    public CalificacionPresenter(ContractCalificacion.ViewC vista) {
        this.vista = vista;
        calificacion = new CalificacionM();
        comentarios = new ArrayList<>();
        porcentaje = new ArrayList<>();
        fotos = new ArrayList<>();
        eventoM = new EventoM();
        eventoPKM = new EventoPKM();
        cliente = new ClienteM();
        eventos = new ArrayList<>();
        calificacionPKM = new CalificacionPKM();
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
    public String obtenerInfoEvento(int id, String horaI, String horaF, String fecha) {
        params = new RequestParams();
        Gson g = new Gson();
        EventoPKM e = new EventoPKM();
        e.setIdevento(id);
        e.setHoraInicio(horaI);
        e.setHoraFinal(horaF);
        e.setFecha(fecha);
        String dato = g.toJson(e);
        opc = 3;
        params.put("consultar", dato);
        String nameServlet = "SERVEvento";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();


        return infoEvento;
    }

    @Override
    public void actualizar(int idEvento) {
        opc = 1;
        params = new RequestParams();
        Gson g = new Gson();
        params.put("listar", String.valueOf(idEvento));
        String nameServlet = "SERVCalificacion";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();


    }

    @Override
    public void update() {
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
            ArrayList<CalificacionM> arrayList = a.fromJson(listaR, listType);
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getComentario() != null) {
                    comentarios.add(arrayList.get(i));
                } else {
                    vista.showResult("Aún no hay comentarios");
                }

                if (arrayList.get(i).getPorcentaje() != null)
                    porcentaje.add(arrayList.get(i).getPorcentaje());

                if (arrayList.get(i).getMultimedia() != null) {
                    fotos.add(arrayList.get(i).getMultimedia());
                } else {
                    vista.showResult("Aún no hay Fotos");
                }

            }
            update();


        } else if (opc == 2) {
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            if (r.equals("true")) {
                vista.showResult("Hecho");
            } else {
                vista.showResult("No se realizó correctamente");
            }
        } else if (opc == 3) {
            infoEvento = "";
            Gson a = new Gson();
            JsonElement c = jo.get("respuesta");
            infoEvento = c.getAsString();
            EventoM event = a.fromJson(infoEvento, EventoM.class);
            vista.mostrarEvento(event);

        }

    }


}