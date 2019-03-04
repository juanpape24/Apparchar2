package com.apparchar.apparchar.Modelo;


/**
 *
 * @author jeffe
 */
public class Calificacion {

    public double porcentaje;
    public String comentario;
    public String multimedia;
    public String hora;
    public int idUser;
    public int idEvento;
    public String fecha;
    public String horaI;
    public String horaF;
    public String fechaE;

    public Calificacion(double porcentaje, String comentario, String multimedia, String hora, int idUser, int idEvento, String fecha, String horaI, String horaF, String fechaE) {
        this.porcentaje = porcentaje;
        this.comentario = comentario;
        this.multimedia = multimedia;
        this.hora = hora;
        this.idUser = idUser;
        this.idEvento = idEvento;
        this.fecha = fecha;
        this.horaI = horaI;
        this.horaF = horaF;
        this.fechaE = fechaE;
    }

    public Calificacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraI() {
        return horaI;
    }

    public void setHoraI(String horaI) {
        this.horaI = horaI;
    }

    public String getHoraF() {
        return horaF;
    }

    public void setHoraF(String horaF) {
        this.horaF = horaF;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    @Override
    public String toString() {
        return "Calificacion{" + "porcentaje=" + porcentaje + ", comentario=" + comentario + ", multimedia=" + multimedia + ", hora=" + hora + ", idUser=" + idUser + ", idEvento=" + idEvento + ", fecha=" + fecha + ", horaI=" + horaI + ", horaF=" + horaF + ", fechaE=" + fechaE + '}';
    }

}