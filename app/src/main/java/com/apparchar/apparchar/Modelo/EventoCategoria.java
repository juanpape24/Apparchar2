package com.apparchar.apparchar.Modelo;

public class EventoCategoria {

    public int idEento;
    public int idCategoria;
    public String horaI;
    public String horaF;
    public String fechaE;

    public EventoCategoria(int idEento, int idCategoria, String horaI, String horaF, String fechaE) {
        this.idEento = idEento;
        this.idCategoria = idCategoria;
        this.horaI = horaI;
        this.horaF = horaF;
        this.fechaE = fechaE;
    }

    public EventoCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdEento() {
        return idEento;
    }

    public void setIdEento(int idEento) {
        this.idEento = idEento;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
        return "EventoCategoria{" + "idEento=" + idEento + ", idCategoria=" + idCategoria + ", horaI=" + horaI + ", horaF=" + horaF + ", fechaE=" + fechaE + '}';
    }

}
