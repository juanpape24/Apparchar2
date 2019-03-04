package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Modelo.UserClient;

public class LoginPresenter implements ContractLogin.PresenterL {

    ContractLogin.ViewL vista;

    public LoginPresenter(ContractLogin.ViewL vista) {
        this.vista=vista;
    }

    @Override
    public void validar(String user, String pass) {
        vista.showResult(user.toString());
    }
}
