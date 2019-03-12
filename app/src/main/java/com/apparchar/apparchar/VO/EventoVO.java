package com.apparchar.apparchar.VO;

public class EventoVO {

    public String nombre;
    public String hora_Inicio;
    public String hora_Final;
    public String direccion;
    public String descripcion;
    public int idEvento;
    public String fecha;

    public EventoVO(String nombre, String hora_Inicio, String hora_Final, String direccion, String descripcion, int idEvento, String fecha) {
        this.nombre = nombre;
        this.hora_Inicio = hora_Inicio;
        this.hora_Final = hora_Final;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.idEvento = idEvento;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora_Inicio() {
        return hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio) {
        this.hora_Inicio = hora_Inicio;
    }

    public String getHora_Final() {
        return hora_Final;
    }

    public void setHora_Final(String hora_Final) {
        this.hora_Final = hora_Final;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Evento{" + "nombre=" + nombre + ", hora_Inicio=" + hora_Inicio + ", hora_Final=" + hora_Final + ", direccion=" + direccion + ", descripcion=" + descripcion + ", idEvento=" + idEvento + ", fecha=" + fecha + '}';
    }

}

