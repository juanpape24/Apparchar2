package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Modelo.Evento;
import com.apparchar.apparchar.VO.CategoriaVO;
import com.apparchar.apparchar.VO.EmpresaVO;
import com.apparchar.apparchar.Vista.CreacionEvento;
import com.apparchar.apparchar.Vista.EventoCreado;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreacionEventoPresenter implements OnLoopjCompleted {

    CreacionEvento vista;
    Evento evento;
    RequestParams params;
    ArrayList cat= new ArrayList();
    public CreacionEventoPresenter(CreacionEvento vista) {
        this.vista = vista;
        evento = new Evento();
        params = new RequestParams();
        Gson g = new Gson();

        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, vista, this);
        loopjTask.executeLoopjCall();


    }

    public void CrearEvento(int id, String nombre, String horaInicio, String horaFinal, String direccion, String descripcion, ArrayList categorias, String fecha) {
        if (nombre.equals("") || horaInicio.equals("") || horaFinal.equals("") || direccion.equals("") || descripcion.equals("") || categorias.isEmpty() || fecha.equals("")) {
            showMensaje("Llene todos los campos");
        } else {
            evento.setIdEvento(id);
            evento.setNombre(nombre);
            evento.setHoraInicio(horaInicio);
            evento.setHoraFinal(horaFinal);
            evento.setDireccion(direccion);
            evento.setDescripcion(descripcion);
            evento.setCategorias(categorias);
            evento.setFecha(fecha);



            String sql="insert into Evento values('"+nombre+"','"+horaInicio+"','"+horaFinal+"','"+direccion+"','"+descripcion+"',"+id+",'"+fecha+"');";

        }




    }

    public void showMensaje(String mensaje) {
        Toast toast1 = Toast.makeText(vista, mensaje, Toast.LENGTH_SHORT);
        toast1.show();
    }

    public Evento getEvento() {
        return evento;
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        Type listType = new TypeToken<ArrayList<CategoriaVO>>(){}.getType();
        Gson a= new Gson();
        ArrayList<CategoriaVO>arrayList= a.fromJson(r,listType);





    }
    public ArrayList getCategorias(){
        return cat;
    }
}

