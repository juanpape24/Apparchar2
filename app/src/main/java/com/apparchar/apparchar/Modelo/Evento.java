package com.apparchar.apparchar.Modelo;

import com.apparchar.apparchar.VO.EmpresaVO;

import java.util.ArrayList;

public class Evento {
    private String nombre;
    private String horaInicio;
    private String horaFinal;
    private Lugar direccion;
    private String descripcion;
    private int idEvento;
    private String fecha;
    private ArrayList<Categoria> EventoCategoria;
    private ArrayList<Calificacion> calificaciones;
    public EmpresaVO empresa;
    private byte[] foto;

    public Evento(String nombre, String horaInicio, String horaFinal, Lugar direccion, String descripcion, String fecha, ArrayList<Categoria> eventoCategoria, ArrayList<Calificacion> calificaciones, EmpresaVO empresa, byte[] foto) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        EventoCategoria = eventoCategoria;
        this.calificaciones = calificaciones;
        this.empresa = empresa;
        this.foto=foto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Evento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Lugar getDireccion() {
        return direccion;
    }

    public void setDireccion(Lugar direccion) {
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

    public ArrayList<Categoria> getEventoCategoria() {
        return EventoCategoria;
    }

    public void setEventoCategoria(ArrayList<Categoria> eventoCategoria) {
        EventoCategoria = eventoCategoria;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }
}
