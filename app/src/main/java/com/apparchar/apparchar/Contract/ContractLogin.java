package com.apparchar.apparchar.Contract;

import java.util.ArrayList;

public interface ContractLogin {

    interface ViewL{
        void showResult(String info);
        void crearEvento(String nit, ArrayList<String> cat);
        void eventc(ArrayList a);
    }
    interface PresenterL{
        void validar(String user,String pass,String type);
    }
}
