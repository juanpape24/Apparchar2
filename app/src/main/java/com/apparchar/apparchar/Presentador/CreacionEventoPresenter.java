package com.apparchar.apparchar.Presentador;

import android.view.View;
import android.widget.Toast;

import com.apparchar.apparchar.Modelo.Evento;

import java.util.ArrayList;

public class CreacionEventoPresenter {

    View vista;
    Evento evento;

    public CreacionEventoPresenter(View vista) {
        this.vista = vista;
        evento = new Evento();
    }

    public void CrearEvento(int id, String nombre, String horaInicio, String horaFinal, String direccion, String descripcion, ArrayList categorias, String fecha) {
        if (nombre.equals("") || horaInicio.equals("") || horaFinal.equals("") || direccion.equals("") || descripcion.equals("") || categorias.isEmpty() || fecha.equals("")) {
            showMensaje("Llene todos los campos");
        } else {
            evento.setIdEvento(id);
            evento.setNombre(nombre);
            evento.setHoraInicio(horaInicio);
            evento.setHoraFinal(horaFinal);
            evento.setDireccion(direccion);
            evento.setDescripcion(descripcion);
            evento.setCategorias(categorias);
            evento.setFecha(fecha);
            String sql="insert into Evento values('"+nombre+"','"+horaInicio+"','"+horaFinal+"','"+direccion+"','"+descripcion+"',"+id+",'"+fecha+"');";

        }




    }

    public void showMensaje(String mensaje) {
        Toast toast1 = Toast.makeText(vista.getContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.show();
    }

    public Evento getEvento() {
        return evento;
    }
}

