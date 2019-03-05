package com.apparchar.apparchar.Modelo;

public class UserEmpresa {
    private String nitEmpresa,nombre,correo,ubicacion, telefono, usuario, contrasenia,descripcion;
    public UserEmpresa(){

    }
    public UserEmpresa(String nitEmpresa, String nombre, String correo, String ubicacion, String telefono, String usuario, String contrasenia, String descripcion) {
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.correo = correo;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String direccion) {
        this.ubicacion = direccion;
    }

    public String getTelefono() {
        return telefono;
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

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "UserEmpresa{" +
                "nitEmpresa='" + nitEmpresa + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contrasenia + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
