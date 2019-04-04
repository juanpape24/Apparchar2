package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EmpresaM;
import com.apparchar.apparchar.Modelo.EmpresaPKM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginPresenter implements ContractLogin.PresenterL, OnLoopjCompleted {

    ContractLogin.ViewL vista;
    RequestParams params;
    String nit;
    String usuarioF = "";
    private String sql = "", sql1;
    private ClienteM cliente;
    private EmpresaM empresa;
    private EmpresaPKM empresaPKM;


    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista = vista;
        cliente = new ClienteM();
        empresa = new EmpresaM();
        empresaPKM=new EmpresaPKM();
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
                cliente.setUsuario(usuario);
                cliente.setContrasenia(contrasenia);
                usuarioF = usuario;
                String envio = g.toJson(cliente);
                params.put("login", envio);
                String nameServlet = "SERVCliente";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();

            } else {
                empresa.setContrasenia(contrasenia);
                empresaPKM.setUsuario(usuario);
                empresa.setEmpresaPK(empresaPKM);
                usuarioF=usuario;
                String envio = g.toJson(empresa);
                params.put("login", envio);

                String nameServlet = "SERVEmpresa";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();
            }
        }

    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement tipoE = jo.get("tipo");
        String tipo = tipoE.getAsString();
        if (tipo.equals("cliente")) {
            JsonElement count = jo.get("count");
            int countR = count.getAsInt();
            if (countR == 1) {
                vista.eventc(usuarioF);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
        } else {
            JsonElement nit = jo.get("nit");
            JsonElement count = jo.get("count");
            String nitR = nit.getAsString();
            int countR = count.getAsInt();
            if (countR == 1) {
                vista.showResult("Correcto");
               vista.crearEvento(nitR,usuarioF);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
        }
    }
}

