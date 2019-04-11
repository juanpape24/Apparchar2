/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

/**
 *
 * @author jeffe
 */
public class CalificacionM {

    private CalificacionPKM calificacionPK;
    private Double porcentaje;
    private String comentario;
    private byte[] multimedia;
    private String hora;
    private String fecha;
    private ClienteM cliente;
    private EventoM evento;

    public CalificacionM() {
    }

    public CalificacionM(CalificacionPKM calificacionPK, Double porcentaje, String comentario, byte[] multimedia, String hora, String fecha, ClienteM cliente, EventoM evento) {
        this.calificacionPK = calificacionPK;
        this.porcentaje = porcentaje;
        this.comentario = comentario;
        this.multimedia = multimedia;
        this.hora = hora;
        this.fecha = fecha;
        this.cliente = cliente;
        this.evento = evento;
    }

    public CalificacionPKM getCalificacionPK() {
        return calificacionPK;
    }

    public void setCalificacionPK(CalificacionPKM calificacionPK) {
        this.calificacionPK = calificacionPK;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public byte[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(byte[] multimedia) {
        this.multimedia = multimedia;
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

    public ClienteM getCliente() {
        return cliente;
    }

    public void setCliente(ClienteM cliente) {
        this.cliente = cliente;
    }

    public EventoM getEvento() {
        return evento;
    }

    public void setEvento(EventoM evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "CalificacionM{" + "calificacionPK=" + calificacionPK + ", porcentaje=" + porcentaje + ", comentario=" + comentario + ", multimedia=" + multimedia + ", hora=" + hora + ", fecha=" + fecha + ", cliente=" + cliente + ", evento=" + evento + '}';
    }

}