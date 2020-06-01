/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author jeffe
 */
public class LugarM {

    private String direccion;
    private String nombre;
    private Double coordenada_x;
    private Double coordenada_y;
    private String descripcion;
    private List<EventoM> lugar;

    public LugarM(String direccion, String nombre, Double coordenadaX, Double coordenadaY, String descripcion, List<EventoM> eventoCollection) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.coordenada_x = coordenadaX;
        this.coordenada_y = coordenadaY;
        this.descripcion = descripcion;
        this.lugar = eventoCollection;
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
        return coordenada_x;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenada_x= coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenada_y;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenada_y = coordenadaY;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EventoM> getEventoCollection() {
        return lugar;
    }

    public void setEventoCollection(List<EventoM> eventoCollection) {
        this.lugar = eventoCollection;
    }

    @Override
    public String toString() {
        return "LugarM{" + "direccion=" + direccion + ", nombre=" + nombre + ", coordenadaX=" + coordenada_x + ", coordenadaY=" + coordenada_y + ", descripcion=" + descripcion + ", eventoCollection=" + lugar + '}';
    }

}
