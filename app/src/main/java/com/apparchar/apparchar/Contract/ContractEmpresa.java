package com.apparchar.apparchar.Contract;

public interface ContractEmpresa{
    interface ViewE{
        void showResultE(String info);
        void cambiar();
    }
    interface PresenterE{
        void signUp(String nit,String nombre,String correo,String direccion,String cel, String user, String pass,String pass2);
    }

}
