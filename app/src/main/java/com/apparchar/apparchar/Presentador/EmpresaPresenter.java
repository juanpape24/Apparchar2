package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractEmpresa;
import com.apparchar.apparchar.Modelo.EmpresaM;
import com.apparchar.apparchar.Modelo.EmpresaPKM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

public class EmpresaPresenter implements ContractEmpresa.PresenterE, OnLoopjCompleted {

    ContractEmpresa.ViewE vista;
    EmpresaM empresa;
    EmpresaPKM empresaPKM;
    RequestParams params;
    public EmpresaPresenter(ContractEmpresa.ViewE vista){
        this.vista=vista;
        empresa=new EmpresaM();
        empresaPKM=new EmpresaPKM();
    }
    @Override
    public void signUp(String nit,String nombre, String correo, String direccion, String cel, String user, String pass,String pass2) {
        if(nit.equals("") || nombre.equals("") || correo.equals("") || direccion.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")){
            vista.showResultE("Llene todos los campos");
        }
        else{
            if(!pass.equals(pass2)){
            vista.showResultE("Las contraseñas no coinciden");
            }
            else{
                empresa.setNombre(nombre);
                empresa.setCorreo(correo);
                empresa.setUbicacion(direccion);
                empresa.setTelefono(cel);
                empresa.setContrasenia(pass);
                empresaPKM.setNitempresa(nit);
                empresa.setDescripcion("sasas");
                vista.cambiar();
                empresa.setEmpresaPK(empresaPKM);
                empresaPKM.setUsuario(user);
                params = new RequestParams();
                Gson g = new Gson();
                String envio = g.toJson(empresa);
                params.put("insertar", envio);
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
        vista.showResultE(r);
        if (r.equals("true")) {
            vista.showResultE("Se registró correctamente");
        } else {
            vista.showResultE("No se pudo hacer el registro");
        }
    }
}
