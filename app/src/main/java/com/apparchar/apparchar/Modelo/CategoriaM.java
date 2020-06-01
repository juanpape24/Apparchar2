/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

import java.util.Collection;

/**
 *
 * @author jeffe
 */
public class CategoriaM {

    private Integer id;
    private String nombre;
    private String icono;
    private Collection<EventoM> eventoCollection;

    public CategoriaM() {
    }

    public CategoriaM(Integer id, String nombre, String icono, Collection<EventoM> eventoCollection) {
        this.id = id;
        this.nombre = nombre;
        this.icono = icono;
        this.eventoCollection = eventoCollection;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Collection<EventoM> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<EventoM> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public String toString() {
        return "CategoriaM{" + "id=" + id + ", nombre=" + nombre + ", icono=" + icono + ", eventoCollection=" + eventoCollection + '}';
    }

}
