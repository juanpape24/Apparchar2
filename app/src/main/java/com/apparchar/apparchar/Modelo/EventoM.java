/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author jeffe
 */

public class EventoM{

    private int id;
    private String nombre;
    private String descripcion;
    private String hora_inicio;
    private String hora_final;
    private String fecha;
    private String lugar;
    private String foto;
    protected EventoPKM eventoPK;
    private Collection<EmpresaM> empresaCollection;

    private Collection<CalificacionM> calificacionCollection;
    private Collection<CategoriaM> categoriaCollection;
    private LugarM direccion;
    private ArrayList<EventoCategoria> evento;
    private String categoria="";

    public EventoM(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFoto() {
        return foto;
    }

    public ArrayList<EventoCategoria> getEvento() {
        return evento;
    }

    public void setEvento(ArrayList<EventoCategoria> evento) {
        this.evento = evento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public EventoPKM getEventoPK() {
        return eventoPK;
    }

    public void setEventoPK(EventoPKM eventoPK) {
        this.eventoPK = eventoPK;
    }

    public Collection<EmpresaM> getEmpresaCollection() {
        return empresaCollection;
    }

    public void setEmpresaCollection(Collection<EmpresaM> empresaCollection) {
        this.empresaCollection = empresaCollection;
    }

    public Collection<CalificacionM> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<CalificacionM> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
    }

    public Collection<CategoriaM> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<CategoriaM> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    public LugarM getDireccion() {
        return direccion;
    }

    public void setDireccion(LugarM direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "EventoM{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", hora_inicio='" + hora_inicio + '\'' +
                ", hora_final='" + hora_final + '\'' +
                ", fecha='" + fecha + '\'' +
                ", lugar='" + lugar + '\'' +
                ", Foto=" + foto +
                ", Evento: "+evento+
                ", Categoria: "+categoria+
                '}';
    }
}
/*public class EventoM {

    protected EventoPKM eventoPK;
    private String nombre;
    private String descripcion;
    private byte[] foto;
    private Collection<EmpresaM> empresaCollection;
    private Collection<CalificacionM> calificacionCollection;
    private Collection<CategoriaM> categoriaCollection;
    private LugarM direccion;

    public EventoM() {
    }

    public EventoM(EventoPKM eventoPK, String nombre, String descripcion, byte[] foto, Collection<EmpresaM> empresaCollection, Collection<CalificacionM> calificacionCollection, Collection<CategoriaM> categoriaCollection, LugarM direccion) {
        this.eventoPK = eventoPK;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.empresaCollection = empresaCollection;
        this.calificacionCollection = calificacionCollection;
        this.categoriaCollection = categoriaCollection;
        this.direccion = direccion;
    }

    public EventoPKM getEventoPK() {
        return eventoPK;
    }

    public void setEventoPK(EventoPKM eventoPK) {
        this.eventoPK = eventoPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Collection<EmpresaM> getEmpresaCollection() {
        return empresaCollection;
    }

    public void setEmpresaCollection(Collection<EmpresaM> empresaCollection) {
        this.empresaCollection = empresaCollection;
    }

    public Collection<CalificacionM> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<CalificacionM> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
    }

    public Collection<CategoriaM> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<CategoriaM> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    public LugarM getDireccion() {
        return direccion;
    }

    public void setDireccion(LugarM direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "EventoM{" + "eventoPK=" + eventoPK + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto=" + foto + ", empresaCollection=" + empresaCollection + ", calificacionCollection=" + calificacionCollection + ", categoriaCollection=" + categoriaCollection + ", direccion=" + direccion + '}';
    }

}*/
