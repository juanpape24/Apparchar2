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
public class ClienteM {

    private String nombre;
    private Integer edad;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasenia;
    private Collection<CalificacionM> calificacionCollection;

    public ClienteM() {
    }

    public ClienteM(String nombre, Integer edad, String correo, String telefono, String usuario, String contrasenia, Collection<CalificacionM> calificacionCollection) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.calificacionCollection = calificacionCollection;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Collection<CalificacionM> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<CalificacionM> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
    }

    @Override
    public String toString() {
        return "ClienteM{" + "nombre=" + nombre + ", edad=" + edad + ", correo=" + correo + ", telefono=" + telefono + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", calificacionCollection=" + calificacionCollection + '}';
    }

}
