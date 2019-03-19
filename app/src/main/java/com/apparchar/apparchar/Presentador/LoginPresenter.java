package com.apparchar.apparchar.Presentador;

import android.content.Context;
import android.content.Intent;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.Empresa;
import com.apparchar.apparchar.Modelo.UserClient;
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
import java.util.concurrent.ExecutionException;

public class LoginPresenter implements ContractLogin.PresenterL, OnLoopjCompleted {

    ContractLogin.ViewL vista;
    RequestParams params;
    String nit;
    private String sql = "", sql1;
    private UserClient cliente;
    private Empresa empresa;
    String usuarioF="";


    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista = vista;
        cliente = new UserClient();
        empresa = new Empresa();
    }

    @Override
    public void validar(String usuario, String contrasenia, String type) {
        String resultado = "";
        if (usuario.equals("") || contrasenia.equals(""))
            vista.showResult("Llene todos los campos");
        else if (type.equals(""))
            vista.showResult("Seleccione si es cliente o empresa");
        else {
            params = new RequestParams();
            Gson g = new Gson();
            if (type.equals("Cliente")) {
                ClienteVO clienteV = new ClienteVO();
                clienteV.setContrasenia(contrasenia);
                clienteV.setUsuario(usuario);
                usuarioF=usuario;
                String envio = g.toJson(clienteV);
                params.put("login", envio);
                String nameServlet = "SERVCliente";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();

            } else {

                EmpresaVO empresaV = new EmpresaVO();
                empresaV.setContrasenia(contrasenia);
                empresaV.setUsuario(usuario);
                String envio = g.toJson(empresaV);
                params.put("login", envio);

                String nameServlet = "SERVEmpresa";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();
            }
        }

    }

    @Override
    public void taskCompleted(String results) {


        ArrayList<String> eventos= new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement tipoE= jo.get("tipo");
        String tipo=tipoE.getAsString();
        if (tipo.equals("cliente")){
            JsonElement evento = jo.get("evento");
            JsonElement id = jo.get("id");
            JsonElement count = jo.get("count");

            int idR = id.getAsInt();
            int countR = count.getAsInt();
            String r = evento.getAsString();
            Type listType = new TypeToken<ArrayList<EventoVO>>() {
            }.getType();
            Gson a = new Gson();
            ArrayList<EventoVO> arrayList = a.fromJson(r, listType);
            for (int i = 0; i < arrayList.size(); i++) {
                eventos.add(arrayList.get(i).getNombre());
            }


            if (countR >= 1) {
                vista.eventc(arrayList,usuarioF);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
        }
        else {
            JsonElement nit = jo.get("nit");
            JsonElement count = jo.get("count");

            String nitR = nit.getAsString();
            int countR = count.getAsInt();




            if (countR >= 1) {
                vista.crearEvento(nitR);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
        }
    }
}

