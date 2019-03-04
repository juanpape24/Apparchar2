package com.apparchar.apparchar.Modelo;

public class Categoria {

    public int id;
    public String nombre;
    public String icono;

    public Categoria(int id, String nombre, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.icono = icono;
    }

    public Categoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", icono=" + icono + '}';
    }

}
