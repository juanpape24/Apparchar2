package com.apparchar.apparchar.Contract;


import com.apparchar.apparchar.Modelo.LugarM;

import java.util.ArrayList;

public interface ContractCreacionEvento{

    interface ViewCE{
        void showResult(String info);
        void mostrarCategorias(ArrayList cat);
        void swap();
        void ingresarFoto();
        void salir();

    }
    interface PresenterCE{
        void crearEvento(String nombre, String horaInicio, String horaFinal, LugarM direccion, String descripcion, ArrayList categorias, String fecha, String nit, byte[] foto, String usuario);
        ArrayList<String> getCategorias();
    }
}
