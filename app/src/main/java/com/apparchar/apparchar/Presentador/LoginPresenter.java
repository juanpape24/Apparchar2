package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.Empresa;
import com.apparchar.apparchar.Modelo.UserClient;
import com.apparchar.apparchar.VO.EmpresaVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

import java.util.concurrent.ExecutionException;

public class LoginPresenter implements ContractLogin.PresenterL, OnLoopjCompleted {

    ContractLogin.ViewL vista;
    RequestParams params;
    String nit;
    private String sql = "", sql1;
    private UserClient cliente;
    private Empresa empresa;

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
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        String g[]=r.split(";");
        vista.showResult(r);
       if (Integer.parseInt(g[0])>= 1) {
            vista.crearEvento(g[1]);
        } else {
            vista.showResult("Usuario y/o contraseña incorrectos");
        }
    }
}

