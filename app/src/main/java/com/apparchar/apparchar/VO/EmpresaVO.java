package com.apparchar.apparchar.VO;

public class EmpresaVO {

    public String nitEmpresa;
    public String nombre;
    public String ubicacion;
    public String telefono;
    public String correo;
    public String descripcion;
    public String usuario;
    public String contrasenia;

    public EmpresaVO(){

    }

    public EmpresaVO(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public EmpresaVO(String nitEmpresa, String nombre, String ubicacion, String telefono, String correo, String descripcion, String usuario, String contrasenia) {
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return "Empresa{" + "nitEmpresa=" + nitEmpresa + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", correo=" + correo + ", descripcion=" + descripcion + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }

}
