package com.apparchar.apparchar.Presentador;

import android.content.Context;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.IO.ApiAdapter;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EmpresaM;
import com.apparchar.apparchar.Modelo.EmpresaPKM;
import com.apparchar.apparchar.Response.ResponseCliente;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ContractLogin.PresenterL{

    ContractLogin.ViewL vista;
    String usuarioF = "";


    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista = vista;
    }

    @Override
    public void validar(String usuario, String contrasenia) {
        usuarioF=usuario;
        if (usuario.equals("") || contrasenia.equals(""))
            vista.showResult("Llene todos los campos");
        else {
            ApiAdapter.getApiService().validacion(usuario,contrasenia).enqueue(new Callback<ResponseCliente>() {
                @Override
                public void onResponse(Call<ResponseCliente> call, Response<ResponseCliente> response) {
                    String rta=response.message();
                    if(rta.equals("OK")){
                        vista.eventc(usuarioF);
                    }
                    else vista.showResult("Usuario y/o contrase\u00f1a incorrectos");
                }

                @Override
                public void onFailure(Call<ResponseCliente> call, Throwable t) {
                    vista.showResult("No se puede conectar al Servidor");
                }
            });


        }

    }

}

