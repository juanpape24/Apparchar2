package com.apparchar.apparchar.Contract;

import android.graphics.Bitmap;

import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.EventoM;

import java.util.ArrayList;

public interface ContractCalificacion {
    interface ViewC{
        void showResult(String mensaje);
        void swap();
        void mostrarFotos(ArrayList<byte[]> fotos);
        void mostrarComentarios(ArrayList<CalificacionM> comentarios);
        void mostrarCalificacion(ArrayList<Double> calificacion);
        void mostrarEvento(EventoM evento);
        String getIdEvento();

    }
    interface PresenterC{
        void crearComentario(String comentario,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        void crearCalificacion(double porcentaje,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        void crearMultimedia(byte[] multimedia,String hora,String user,int idEvento,String fecha,String horaI,String horaF,String fechaE);
        String obtenerInfoEvento(int id, String horaI, String horaF, String fechaEvento);
        void actualizar(int idEvento);
    }
}
