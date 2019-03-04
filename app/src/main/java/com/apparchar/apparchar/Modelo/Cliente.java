package com.apparchar.apparchar.Modelo;

public class Cliente {

    public int id;
    public String nombre;
    public int edad;
    public String correo;
    public String telefono;
    public String usuario;
    public String contrasenia;

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(int id, String nombre, int edad, String correo, String telefono, String usuario, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Cliente() {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", correo=" + correo + ", telefono=" + telefono + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }

}