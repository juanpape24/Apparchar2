package com.apparchar.apparchar.Contract;

public interface ContractLogin {

    interface ViewL{
        void showResult(String info);
        void crearEvento();
    }
    interface PresenterL{
        void validar(String user,String pass,String type);
    }
}
