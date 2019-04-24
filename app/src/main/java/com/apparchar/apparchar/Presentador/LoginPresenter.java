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
    public void validar(String usuario, String contrasenia) {
        String resultado = "";
        if (usuario.equals("") || contrasenia.equals(""))
            vista.showResult("Llene todos los campos");
        else {
            params = new RequestParams();
            Gson g = new Gson();
                cliente.setUsuario(usuario);
                cliente.setContrasenia(contrasenia);
                usuarioF = usuario;
                String envio = g.toJson(cliente);
                params.put("login", envio);
                String nameServlet = "SERVCliente";
                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();


        }

    }

    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
            JsonElement count = jo.get("count");
            int countR = count.getAsInt();
            if (countR == 1) {
                vista.eventc(usuarioF);
            } else {
                vista.showResult("Usuario y/o contraseña incorrectos");
            }
    }
}

