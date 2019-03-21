package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractEmpresa;
import com.apparchar.apparchar.Modelo.UserEmpresa;
import com.apparchar.apparchar.VO.ClienteVO;
import com.apparchar.apparchar.VO.EmpresaVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

import java.util.concurrent.ExecutionException;

public class EmpresaPresenter implements ContractEmpresa.PresenterE, OnLoopjCompleted {

    ContractEmpresa.ViewE vista;
    UserEmpresa empresa;
    RequestParams params;
    public EmpresaPresenter(ContractEmpresa.ViewE vista){
        this.vista=vista;
        empresa=new UserEmpresa();
    }
    @Override
    public void signUp(String nombre, String correo, String direccion, String cel, String user, String pass,String pass2) {
        if(nombre.equals("") || correo.equals("") || direccion.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")){
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
                empresa.setUsuario(user);
                empresa.setContrasenia(pass);
                int x= (int) (Math.random()*9999+1);
                empresa.setNitEmpresa(String.valueOf(x));
                empresa.setDescripcion("sasas");
                vista.cambiar();
                EmpresaVO empresaV = new EmpresaVO(empresa.getNitEmpresa(),empresa.getNombre(),empresa.getUbicacion(),empresa.getTelefono(),empresa.getCorreo(),empresa.getDescripcion(),empresa.getUsuario(),empresa.getContrasenia());
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
