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
public class EmpresaPKM {

    private String nitempresa;
    private String usuario;

    public EmpresaPKM(String nitempresa, String usuario) {
        this.nitempresa = nitempresa;
        this.usuario = usuario;
    }

    public EmpresaPKM() {
    }

    public String getNitempresa() {
        return nitempresa;
    }

    public void setNitempresa(String nitempresa) {
        this.nitempresa = nitempresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "EmpresaPKM{" + "nitempresa=" + nitempresa + ", usuario=" + usuario + '}';
    }

}
