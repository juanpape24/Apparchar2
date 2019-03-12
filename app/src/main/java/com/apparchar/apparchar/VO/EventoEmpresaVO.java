package com.apparchar.apparchar.VO;

/**
 *
 * @author jeffe
 */
public class EventoEmpresaVO {

    public int nitEmpresa;
    public int idEvento;
    public String horaI;
    public String horaF;
    public String fechaE;

    public EventoEmpresaVO(int nitEmpresa, int idEvento, String horaI, String horaF, String fechaE) {
        this.nitEmpresa = nitEmpresa;
        this.idEvento = idEvento;
        this.horaI = horaI;
        this.horaF = horaF;
        this.fechaE = fechaE;
    }

    public int getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(int nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

}
