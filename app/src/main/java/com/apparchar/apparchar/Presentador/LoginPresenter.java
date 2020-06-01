package com.apparchar.apparchar.Presentador;

import android.util.Log;

import com.apparchar.apparchar.Conexion.JsonApi;
import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.ClienteM;

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
            JsonApi.getApiService().validacion(usuario,contrasenia).enqueue(new Callback<ClienteM>() {
                @Override
                public void onResponse(Call<ClienteM> call, Response<ClienteM> response) {
                    String rta=response.message();
                    if(rta.equals("OK")){
                        vista.eventc(usuarioF);
                    }
                    else vista.showResult("Usuario y/o contrase\u00f1a incorrectos");
                }

                @Override
                public void onFailure(Call<ClienteM> call, Throwable t) {
                    Log.i("error",t.getMessage());
                    vista.showResult("No se puede conectar al Servidor");
                }
            });


        }

    }

}

