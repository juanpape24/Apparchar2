package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCreacionEvento;
import com.apparchar.apparchar.Modelo.Categoria;
import com.apparchar.apparchar.Modelo.Evento;
import com.apparchar.apparchar.Modelo.Lugar;
import com.apparchar.apparchar.VO.CategoriaVO;
import com.apparchar.apparchar.VO.ClienteVO;
import com.apparchar.apparchar.VO.EmpresaVO;
import com.apparchar.apparchar.VO.EventoVO;
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

public class CreacionEventoPresenter implements OnLoopjCompleted, ContractCreacionEvento.PresenterCE {

    ContractCreacionEvento.ViewCE vista;
    Evento evento;
    RequestParams params;
    ArrayList<String> cat;
    ArrayList<Categoria> categoria = new ArrayList<>();
    int opc=0;

    public CreacionEventoPresenter(ContractCreacionEvento.ViewCE vista) {
        this.vista = vista;
        evento = new Evento();
        params = new RequestParams();
        cat= new ArrayList<>();
        Gson g = new Gson();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
    }




    public Evento getEvento() {
        return evento;
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        if (opc==1){
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            if (r.equals("true")){
                vista.showResult("Se ha registrado correctamente");
            }
            else {vista.showResult("No se ha registrado correctamente");}

        } else{
            JsonElement c = jo.get("categoria");
            String r = c.getAsString();
            Type listType = new TypeToken<ArrayList<CategoriaVO>>() {
            }.getType();
            Gson a = new Gson();
            ArrayList<CategoriaVO> arrayList = a.fromJson(r, listType);
            for (int i = 0; i < arrayList.size(); i++) {
                cat.add(arrayList.get(i).getNombre());
            }

        }





    }


    @Override
    public void crearEvento(int id, String nombre, String horaInicio, String horaFinal, Lugar direccion, String descripcion, ArrayList categorias, String fecha, String nit) {
        if (nombre.equals("") || horaInicio.equals("") || horaFinal.equals("") || descripcion.equals("") || categorias.isEmpty() || fecha.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            evento.setIdEvento(id);
            evento.setNombre(nombre);
            evento.setHoraInicio(horaInicio);
            evento.setHoraFinal(horaFinal);
            evento.setDireccion(direccion);
            evento.setDescripcion(descripcion);
            for (int i = 0; i < categorias.size(); i++) {
                Categoria c = new Categoria();
                c.setId((int) categorias.get(i));
                categoria.add(c);
            }
            evento.setEventoCategoria(categoria);
            evento.setFecha(fecha);
            evento.setEmpresa(new EmpresaVO(nit));
            params = new RequestParams();
            Gson g = new Gson();
            String envio = g.toJson(evento);
            params.put("insertar", envio);
            params.put("horai", horaInicio);
            params.put("horaf", horaFinal);
            String nameServlet = "SERVEvento";
            MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
            loopjTask.executeLoopjCall();
            opc=1;
        }
    }

    @Override
    public ArrayList<String> getCategorias() {
        return cat;


    }
}

