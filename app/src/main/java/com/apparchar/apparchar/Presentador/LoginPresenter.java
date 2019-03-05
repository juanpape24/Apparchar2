package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.MyATaskCliente;
import com.apparchar.apparchar.Modelo.Token;
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
                Token token = new Token("generico", "login", sql1);
                MyATaskCliente myCliente = new MyATaskCliente(token);
                try {
                    resultado = myCliente.execute(resultado).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                sql1 = "select count(*) from empresa where usuario='" + usuario + "' and contrasenia='" + contrasenia + "'";
                Token token = new Token("generico", "login", sql1);
                MyATaskCliente myCliente = new MyATaskCliente(token);
                try {
                    resultado = myCliente.execute(resultado).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            if (Integer.parseInt(resultado) >= 1)
                vista.showResult("Realizado");
            else
                vista.showResult("Usuario y/o contraseña incorrectos ");
        }

    }

}

