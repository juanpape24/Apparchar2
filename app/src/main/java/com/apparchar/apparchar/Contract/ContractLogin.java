package com.apparchar.apparchar.Contract;

public interface ContractLogin {

    interface ViewL{
        void showResult(String info);
    }
    interface PresenterL{
        void validar(String user,String pass,String type);
    }
}
