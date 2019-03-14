package com.apparchar.apparchar.Modelo;

public class Categoria {

    public int id;
    public String nombre;
    public byte[] icono;

    public Categoria(int id, String nombre, byte[] icono) {
        this.id = id;
        this.nombre = nombre;
        this.icono = icono;
    }

    public Categoria() {

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

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", icono=" + icono + '}';
    }

}
