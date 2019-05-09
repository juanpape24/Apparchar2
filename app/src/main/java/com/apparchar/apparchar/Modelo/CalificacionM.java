/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

import java.util.Collection;

/**
 *
 * @author jeffe
 */
public class CalificacionM {

    private Integer idcalificacion;
    private Double porcentaje;
    private String comentario;
    private byte[] multimedia;
    private String hora;
    private String fecha;
    private Collection<EventoM> eventoCollection;
    private ClienteM usuariocliente;

    public CalificacionM(Integer idcalificacion, Double porcentaje, String comentario, byte[] multimedia, String hora, String fecha, Collection<EventoM> eventoCollection, ClienteM usuariocliente) {
        this.idcalificacion = idcalificacion;
        this.porcentaje = porcentaje;
        this.comentario = comentario;
        this.multimedia = multimedia;
        this.hora = hora;
        this.fecha = fecha;
        this.eventoCollection = eventoCollection;
        this.usuariocliente = usuariocliente;
    }

    public CalificacionM() {
    }

    public Integer getIdcalificacion() {
        return idcalificacion;
    }

    public void setIdcalificacion(Integer idcalificacion) {
        this.idcalificacion = idcalificacion;
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

    public Collection<EventoM> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<EventoM> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    public ClienteM getUsuariocliente() {
        return usuariocliente;
    }

    public void setUsuariocliente(ClienteM usuariocliente) {
        this.usuariocliente = usuariocliente;
    }

    @Override
    public String toString() {
        return "CalificacionM{" + "idcalificacion=" + idcalificacion + ", porcentaje=" + porcentaje + ", comentario=" + comentario + ", multimedia=" + multimedia + ", hora=" + hora + ", fecha=" + fecha + ", eventoCollection=" + eventoCollection + ", usuariocliente=" + usuariocliente + '}';
    }

}
