package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.UserClient;

import java.util.concurrent.ExecutionException;

public class LoginPresenter implements ContractLogin.PresenterL {

    ContractLogin.ViewL vista;
    private String sql = "", sql1;
    private UserClient cliente;

    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista = vista;
        cliente = new UserClient();
    }

    @Override
    public void validar(String usuario, String contrasenia, String type) {
        String resultado = "";
        if (usuario.equals("") || contrasenia.equals(""))
            vista.showResult("Llene todos los campos");
        else if (type.equals(""))
            vista.showResult("Seleccione si es cliente o empresa");
        else {
            if (type.equals("Cliente")) {
                sql1 = "select count(*) from cliente where usuario='" + usuario + "' and contrasenia='" + contrasenia + "'";

            } else {
                sql1 = "select count(*) from empresa where usuario='" + usuario + "' and contrasenia='" + contrasenia + "'";


            }
            resultado = "1";
            if (Integer.parseInt(resultado) >= 1)
                vista.crearEvento();
            else
                vista.showResult("Usuario y/o contraseña incorrectos ");
        }

    }

}

