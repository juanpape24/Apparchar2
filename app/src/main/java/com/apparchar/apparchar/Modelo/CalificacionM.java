/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apparchar.apparchar.Modelo;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jeffe
 */
public class CalificacionM{

    private Integer idcalificacion;
    private Double porcentaje;
    private String comentario;
    private String multimedia;
    private String hora;
    //private String hora;
    private String fecha;
    //private String fecha;
    private String usuariocliente;
    private int evento;

    public CalificacionM(Integer idcalificacion, Double porcentaje, String comentario, String multimedia, String hora, String fecha, String usuariocliente, int evento) {
        this.idcalificacion = idcalificacion;
        this.porcentaje = porcentaje;
        this.comentario = comentario;
        this.multimedia = multimedia;
        this.hora = hora;
        this.fecha = fecha;
        this.usuariocliente = usuariocliente;
        this.evento = evento;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuariocliente() {
        return usuariocliente;
    }

    public void setUsuariocliente(String usuariocliente) {
        this.usuariocliente = usuariocliente;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }


    @Override
    public String toString() {
        return "CalificacionM{" +
                "idcalificacion=" + idcalificacion +
                ", porcentaje=" + porcentaje +
                ", comentario='" + comentario + '\'' +
                ", multimedia='" + multimedia + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", usuariocliente=" + usuariocliente +
                ", evento=" + evento +
                '}';
    }
}
