package com.apparchar.apparchar.Presentador;


import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractClient;

import com.apparchar.apparchar.Modelo.ClienteM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;


public class RegistrarPresenter implements ContractClient.Presenter, OnLoopjCompleted {
    ClienteM cliente;
    RequestParams params;

    private ContractClient.View vista;


    /**
     * HOST
     */

    public RegistrarPresenter(ContractClient.View vista) {
        this.vista = vista;
        cliente = new ClienteM();
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2) {
        if (nombre.equals("") || apellido.equals("") || edad.equals("") || correo.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            if (!pass.equals(pass2)) {
                vista.showResult("Las contraseñas no coinciden");
            } else {
                cliente.setNombre(nombre);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setTelefono(cel);
                cliente.setUsuario(user);
                cliente.setContrasenia(pass);
                vista.swap();
                params = new RequestParams();
                Gson g = new Gson();


                String envio = g.toJson(cliente);

                params.put("insertar", envio);
                String nameServlet = "SERVCliente";


                MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet, (Context) vista, this);
                loopjTask.executeLoopjCall();
                //vista.showResult("Registro op: ");
            }

        }
    }


    @Override
    public void taskCompleted(String results) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        vista.showResult(r);
        if (r.equals("true")) {
            vista.showResult("Se registró correctamente");
        } else {
            vista.showResult("El usuario ya existe");
        }
    }
}

