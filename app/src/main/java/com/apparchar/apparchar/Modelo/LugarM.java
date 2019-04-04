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
public class LugarM {

    private String direccion;
    private String nombre;
    private Double coordenadaX;
    private Double coordenadaY;
    private String descripcion;
    private Collection<EventoM> eventoCollection;

    public LugarM(String direccion, String nombre, Double coordenadaX, Double coordenadaY, String descripcion, Collection<EventoM> eventoCollection) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.descripcion = descripcion;
        this.eventoCollection = eventoCollection;
    }

    public LugarM() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<EventoM> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<EventoM> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public String toString() {
        return "LugarM{" + "direccion=" + direccion + ", nombre=" + nombre + ", coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + ", descripcion=" + descripcion + ", eventoCollection=" + eventoCollection + '}';
    }

}
