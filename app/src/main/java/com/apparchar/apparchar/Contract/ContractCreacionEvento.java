package com.apparchar.apparchar.Contract;


import com.apparchar.apparchar.Modelo.Lugar;

import java.util.ArrayList;

public interface ContractCreacionEvento {
    interface ViewCE{
        void showResult(String info);
        void mostrarCategorias(ArrayList cat);
        void swap();
        void ingresarFoto();
    }
    interface PresenterCE{
        void crearEvento(String nombre, String horaInicio, String horaFinal, Lugar direccion, String descripcion, ArrayList categorias, String fecha, String nit, byte[] foto);
        ArrayList<String> getCategorias();
    }
}
