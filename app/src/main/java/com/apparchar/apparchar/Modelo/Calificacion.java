package com.apparchar.apparchar.Modelo;


/**
 *
 * @author jeffe
 */
public class Calificacion {
    private float porcentaje;
    private String comentario, hora,fecha,horaI,horaF,fechaE;
    private byte[] multimedia;
    private int idUser,idEvento;



    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public Object getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(byte[] multimedia) {
        this.multimedia = multimedia;
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

    @Override
    public String toString() {
        return "Calificacion{" +
                "porcentaje=" + porcentaje +
                ", comentario='" + comentario + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", horaI='" + horaI + '\'' +
                ", horaF='" + horaF + '\'' +
                ", fechaE='" + fechaE + '\'' +
                ", multimedia=" + multimedia +
                ", idUser=" + idUser +
                ", idEvento=" + idEvento +
                '}';
    }
}
