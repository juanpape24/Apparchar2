package com.apparchar.apparchar.Contract;

import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.EventoPKM;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public interface ContractCalificacion {
    interface ViewC{
        void showResult(String mensaje);
        void swap();
        void mostrarFotos(ArrayList<String> fotos);
        void mostrarComentarios(ArrayList<CalificacionM> comentarios, ArrayList<ClienteM> clientes);
        void mostrarCalificacion(ArrayList<Double> calificacion);
        void mostrarEvento(EventoM evento);

    }
    interface PresenterC{
        void crearComentario(String comentario, String hora, String user, String fecha);
        void crearCalificacion(double porcentaje,String hora,String user,String fecha);
        void crearMultimedia(File multimedia, String hora, String user, String fecha);
        //String obtenerInfoEvento(int id, String horaI, String horaF, String fechaEvento);
        void actualizar(String idEvento);
        void update();
        void getEvento(String idEvento);
    }
}
