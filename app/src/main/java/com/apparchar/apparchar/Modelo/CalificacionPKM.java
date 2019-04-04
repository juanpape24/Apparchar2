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
public class CalificacionPKM {

    private String usuariocliente;
    private int idevento;

    public CalificacionPKM(String usuariocliente, int idevento) {
        this.usuariocliente = usuariocliente;
        this.idevento = idevento;
    }

    public CalificacionPKM() {
    }

    public String getUsuariocliente() {
        return usuariocliente;
    }

    public void setUsuariocliente(String usuariocliente) {
        this.usuariocliente = usuariocliente;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    @Override
    public String toString() {
        return "CalificacionPKM{" + "usuariocliente=" + usuariocliente + ", idevento=" + idevento + '}';
    }

}
