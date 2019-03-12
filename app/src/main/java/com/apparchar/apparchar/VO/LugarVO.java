package com.apparchar.apparchar.VO;

public class LugarVO {

    public String direccion;
    public String nombre;
    public String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Luga{" + "direccion=" + direccion + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
