package com.apparchar.apparchar.Presentador;


import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractClient;

import com.apparchar.apparchar.IO.ApiAdapter;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrarPresenter implements ContractClient.Presenter{
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
                vista.showResult("Las contrase\u00f1as no coinciden");
            } else {
                cliente.setNombre(nombre);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setTelefono(cel);
                cliente.setUsuario(user);
                cliente.setContrasenia(pass);
                vista.swap();
                params = new RequestParams();
                ApiAdapter.getApiService().registro(cliente).enqueue(new Callback<ClienteM>() {
                    @Override
                    public void onResponse(Call<ClienteM> call, Response<ClienteM> response) {
                        vista.showResult("Se registr\u00f3 correctamente");
                    }

                    @Override
                    public void onFailure(Call<ClienteM> call, Throwable t) {
                        vista.showResult("El usuario ya existe");
                    }
                });

            }

        }
    }



}

