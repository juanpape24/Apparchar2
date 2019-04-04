package com.apparchar.apparchar.Contract;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface ContractCalificacion {
    interface ViewC{
        void showResult(String mensaje);
        void swap();
        void mostrarFotos(ArrayList<byte[]> fotos);
        void mostrarComentarios(ArrayList<String> comentarios);
        void mostrarCalificacion(ArrayList<Double> calificacion);
        String getIdEvento();

    }
    interface PresenterC{
        void crearComentario(String comentario,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        void crearCalificacion(double porcentaje,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        void crearMultimedia(byte[] multimedia,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        String obtenerInfoEvento();
        void actualizar(int idEvento);
    }
}
