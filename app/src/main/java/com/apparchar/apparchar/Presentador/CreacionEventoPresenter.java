package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.util.Log;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractCreacionEvento;
import com.apparchar.apparchar.Modelo.CategoriaM;
import com.apparchar.apparchar.Modelo.EmpresaM;
import com.apparchar.apparchar.Modelo.EmpresaPKM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.EventoPKM;
import com.apparchar.apparchar.Modelo.LugarM;
import com.apparchar.apparchar.Vista.Empresa;
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
    EventoM evento;
    RequestParams params;
    ArrayList<String> cat=new ArrayList<>();
    EventoPKM eventoPKM;
    ArrayList<CategoriaM> categoria = new ArrayList<>();
    int opc = 0;
    ArrayList<EmpresaM> empresas = new ArrayList<>();

    public CreacionEventoPresenter(ContractCreacionEvento.ViewCE vista) {
        this.vista = vista;
        evento = new EventoM();
        params = new RequestParams();
        Gson g = new Gson();
        eventoPKM = new EventoPKM();
        params.put("listar", "xd");
        String nameServlet = "SERVCategoria";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
        loopjTask.executeLoopjCall();
    }


    public EventoM getEvento() {
        return evento;
    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        if (opc == 1) {
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            if (r.equals("true")) {
                vista.showResult("Se ha registrado correctamente");
                vista.swap();
                vista.mostrarCategorias(cat);

            } else {
                vista.showResult("No se ha registrado correctamente");
            }


        } else {
            JsonElement c = jo.get("respuesta");
            String r = c.getAsString();
            Type listType = new TypeToken<ArrayList<CategoriaM>>() {
            }.getType();
            Gson a = new Gson();
            ArrayList<CategoriaM> arrayList = a.fromJson(r, listType);
            for (int i = 0; i < arrayList.size(); i++) {
                cat.add(arrayList.get(i).getNombre());
            }
            vista.mostrarCategorias(cat);



        }


    }


    @Override
    public void crearEvento(String nombre, String horaInicio, String horaFinal, LugarM direccion, String descripcion, ArrayList categorias, String fecha, String nit, byte[] foto, String usuario) {
        if (nombre.equals("") || horaInicio.equals("") || horaFinal.equals("") || descripcion.equals("") || categorias.isEmpty() || fecha.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            evento=new EventoM();
            eventoPKM= new EventoPKM();
            evento.setNombre(nombre);
            eventoPKM.setHoraInicio(horaInicio);
            eventoPKM.setHoraFinal(horaFinal);
            evento.setDireccion(direccion);
            evento.setDescripcion(descripcion);
            if (foto != null) {
                evento.setFoto(foto);
            }
            categoria=new ArrayList<>();
            for (int i = 0; i < categorias.size(); i++) {
                CategoriaM c = new CategoriaM();
                c.setId((int) categorias.get(i));
                categoria.add(c);
            }
          //  vista.showResult(categoria.toString());
            evento.setCategoriaCollection(categoria);
            eventoPKM.setFecha(fecha);
            EmpresaM empresa = new EmpresaM();
            EmpresaPKM empresaPKM = new EmpresaPKM();

            empresaPKM.setNitempresa(nit);
            empresaPKM.setUsuario(usuario);
            empresa.setEmpresaPK(empresaPKM);

            empresas.add(empresa);
            evento.setEmpresaCollection(empresas);
            empresas=new ArrayList<>();
            evento.setEventoPK(eventoPKM);
            params = new RequestParams();
            Gson g = new Gson();
            String envio = g.toJson(evento);
            params.put("insertar", envio);
            params.put("horai", horaInicio);
            params.put("horaf", horaFinal);
            String nameServlet = "SERVEvento";
            MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
            loopjTask.executeLoopjCall();
            opc = 1;
        }
    }

    @Override
    public ArrayList<String> getCategorias() {
        return cat;
    }


}

