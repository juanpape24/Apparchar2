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
public class EventoPKM{

    private String horaInicio;
    private String horaFinal;
    private int idevento;
    private String fecha;

    public EventoPKM() {
    }

    public EventoPKM(String horaInicio, String horaFinal, int idevento, String fecha) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.idevento = idevento;
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "EventoPKM{" + "horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + ", idevento=" + idevento + ", fecha=" + fecha + '}';
    }

}
